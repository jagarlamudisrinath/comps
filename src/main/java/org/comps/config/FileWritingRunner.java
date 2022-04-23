package org.comps.config;

import org.comps.model.ChatMessage;
import org.comps.service.FileWritingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FileWritingRunner implements CommandLineRunner {
    @Autowired private FileWritingService fileWritingService;

    @Override
    public void run(String... args) {
        fileWritingService.sendMessageToFile(getPayload("2", "msg2", "sender2"));
    }

    private ChatMessage getPayload(String chatId, String msg, String sender) {
        ChatMessage message = new ChatMessage();
        message.setChatId(chatId);
        message.setContent(msg);
        message.setType(ChatMessage.MessageType.CHAT);
        message.setSender(sender);
        message.setCreatedOn(new Date());
        return message;
    }
}
