package org.comps.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "messages")
public class ChatMessages {
    private List<ChatMessage> messages = new ArrayList<>();

    public ChatMessages() {
    }

    public ChatMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    @XmlElement(name = "message")
    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }
}
