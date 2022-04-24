package org.comps.controller;

import org.comps.model.ChatMessage;
import org.comps.service.ChatMessageService;
import org.comps.service.InputChannelService;
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
import java.util.UUID;

@Component
public class WebSocketEventListener {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired private SimpMessageSendingOperations messageSendingOperations;
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private InputChannelService fileWritingService;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        saveAndSendMessage(headerAccessor, ChatMessage.MessageType.JOINED);
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
            if(messageType == ChatMessage.MessageType.JOINED) {
                message.setType(messageType);
                message.setContent("User joined");
            } else if(messageType == ChatMessage.MessageType.LEFT) {
                message.setType(messageType);
                message.setContent("User left");
            }
            message.setChatId(chatId);
            message.setNew(true);
            message.setSender(username);
            message.setCreatedOn(new Date());
            message.setId(UUID.randomUUID().toString());
            chatMessageService.save(message);
            messageSendingOperations.convertAndSend("/topic/" + chatId, message);
            fileWritingService.sendMessageToInputChannel(message);
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        saveAndSendMessage(headerAccessor, ChatMessage.MessageType.LEFT);
    }
}
