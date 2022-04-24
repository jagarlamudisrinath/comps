package org.comps.service;

import org.comps.model.ChatMessage;
import org.comps.model.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

@Service
public class FileWritingService {
    private DocumentBuilder documentBuilder;
    private Transformer transformer;
    @Autowired private GroupService groupService;
    private Map<String, String> groupIdVsName = new WeakHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(InputChannelService.class);

    public FileWritingService() {
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        } catch(ParserConfigurationException | TransformerConfigurationException ex) {
            throw new RuntimeException("Unable create dom object", ex);
        }
    }

    public void writeMessagesToFile(List<ChatMessage> messages) {
        Map<String, List<ChatMessage>> groupVsMsgs = messages.stream().collect(Collectors.groupingBy(msg -> msg.getChatId()));
        for(Map.Entry<String, List<ChatMessage>> entry : groupVsMsgs.entrySet()) {
            try {
                File file = createFileIfNotExists(entry.getKey());
                Document document = documentBuilder.parse(file);
                Node firstChild = document.getFirstChild();
                for(ChatMessage chatMessage : entry.getValue()) {
                    Element message = document.createElement("message");
                    message.setAttribute("createdOn", chatMessage.getCreatedOn().toString());
                    message.setAttribute("sender", chatMessage.getSender());
                    message.setAttribute("type", chatMessage.getType().name());
                    message.setTextContent(chatMessage.getContent());
                    firstChild.appendChild(message);
                }
                //initialize StreamResult with File object to save to file
                StreamResult result = new StreamResult(file);
                DOMSource source = new DOMSource(document);
                transformer.transform(source, result);
            } catch (SAXException | IOException | TransformerException e) {
                logger.error(" Exception while reading files : {}", entry.getKey(), e);
            }
        }
    }

    private File createFileIfNotExists(String groupId) {
        String groupName = groupIdVsName.get(groupId);
        if(!StringUtils.hasText(groupName)) {
            Group group = groupService.findById(groupId);
            if(group != null) {
                groupIdVsName.put(groupId, group.getTitle());
            }
        }
        String fileName = "output-xmls/" + groupId + ".xml";
        File file = new File(fileName);
        if(file.exists()) {
            return file;
        }
        try(FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("<messages groupId=\""+ groupId +"\" groupName=\"" + groupName + "\"></messages>");
        } catch(IOException ex) {
            logger.error("File not found exception : {}", ex.getMessage(), ex);
        }
        return file;
    }
}
