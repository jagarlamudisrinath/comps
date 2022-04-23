package org.comps.service;

import org.comps.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public class FileWritingService {
    @Autowired @Qualifier("inputChannel")
    private MessageChannel fileChannel;

    public void sendMessageToFile(ChatMessage msg) {
        Message<ChatMessage> message = MessageBuilder.withPayload(msg)
                .setHeader("file_name", msg.getChatId() + ".xml").build();
        fileChannel.send(message);
    }
}
