package org.comps.controller;

import org.comps.model.ChatMessage;
import org.comps.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class ChatController {
    @Autowired private SimpMessageSendingOperations messageSendingOperations;
    @Autowired private ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage message) {
        if(!StringUtils.hasText(message.getId())) {
            message.setId(UUID.randomUUID().toString());
        }
        message.setType(ChatMessage.MessageType.CHAT);
        message.setCreatedOn(new Date());
        message.setNew(true);
        if(message.isPersist()) {
            chatMessageService.save(message);
        } else {
            messageSendingOperations.convertAndSend("/topic/" + message.getChatId(), message);
        }
    }

    @GetMapping("/messages/{groupId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages (@PathVariable String groupId,
            @RequestParam Integer page, @RequestParam Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdOn"));
        return ResponseEntity.ok(chatMessageService.findChatMessagesByGroupId(groupId, pageRequest));
    }
}
