package org.comps.controller;

import org.comps.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
        String username = null;
        if (headerAccessor.getMessageHeaders().containsKey("simpUser")) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken)headerAccessor.getMessageHeaders().get("simpUser");
            username = usernamePasswordAuthenticationToken.getName();
        }

        String chatId = null;
        if(headerAccessor.getMessageHeaders().containsKey("simpConnectMessage")) {
            GenericMessage msg = (GenericMessage)headerAccessor.getMessageHeaders().get("simpConnectMessage");
            if(msg.getHeaders().containsKey("nativeHeaders")) {
                Map<String, List<String>> nativeHeaders = (Map<String, List<String>>) msg.getHeaders().get("nativeHeaders");
                chatId = nativeHeaders.get("groupId").get(0);
            }
        }
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
