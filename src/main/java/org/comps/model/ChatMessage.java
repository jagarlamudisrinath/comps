package org.comps.model;

import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("group_discussions")
public class ChatMessage extends BaseEntity {
    private String chatId;
    private String content;
    private String sender;
    private Date   createdOn;
    private MessageType type;

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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public enum MessageType {
        JOIN, CHAT, LEAVE
    }
}
