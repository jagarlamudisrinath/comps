package org.comps.model;

import org.springframework.data.relational.core.mapping.Table;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

@XmlRootElement(name = "message")
@Table("group_discussions")
public class ChatMessage extends BaseEntity {
    private String chatId;
    private String content;
    private String sender;
    private Date   createdOn;
    private MessageType type;
    private boolean persist;

    @XmlAttribute
    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @XmlAttribute
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @XmlAttribute(name = "groupId")
    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    @XmlAttribute
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public enum MessageType {
        JOINED, CHAT, LEFT
    }

    @XmlTransient
    public boolean isPersist() {
        return persist;
    }

    public void setPersist(boolean persist) {
        this.persist = persist;
    }
}
