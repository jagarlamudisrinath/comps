package org.comps.service;

import org.comps.model.ChatMessage;
import org.comps.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ChatMessageService {
    @Autowired private ChatMessageRepository chatMessageRepository;

    public void save(ChatMessage message) {
        chatMessageRepository.save(message);
    }

    public List<ChatMessage> findChatMessagesByGroupId(String groupId, Pageable pageable) {
        return chatMessageRepository.findChatMessageByChatId(groupId, pageable);
    }
}
