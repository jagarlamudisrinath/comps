package org.comps.controller;

import org.comps.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Date;

@Component
public class WebSocketEventListener {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messageSendingOperations;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        saveAndSendMessage(headerAccessor, ChatMessage.MessageType.JOIN);
    }

    private void saveAndSendMessage(StompHeaderAccessor headerAccessor, ChatMessage.MessageType messageType) {
        String username = (String) headerAccessor.getSessionAttributes().get("userId");
        String chatId = (String) headerAccessor.getSessionAttributes().get("chatId");
        if (username != null && chatId != null) {
            logger.info ("user {} connected", username);
            ChatMessage message = new ChatMessage();
            if(messageType == ChatMessage.MessageType.JOIN) {
                message.setType(messageType);
                message.setContent("User joined");
            } else if(messageType == ChatMessage.MessageType.LEAVE) {
                message.setType(messageType);
                message.setContent("User left");
            }
            message.setNew(true);
            message.setSender(username);
            message.setCreatedOn(new Date());
            messageSendingOperations.convertAndSend("/topic/" + chatId, message);
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        saveAndSendMessage(headerAccessor, ChatMessage.MessageType.LEAVE);
    }
}
