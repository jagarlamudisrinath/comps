package org.comps.repository;

import org.comps.model.ChatMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ChatMessageRepository extends PagingAndSortingRepository<ChatMessage, String> {

    List<ChatMessage> findChatMessageByChatId(String chatId, Pageable pageable);
}
