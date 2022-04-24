package org.comps.config;

import org.comps.model.ChatMessage;
import org.comps.service.InputChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FileWritingRunner implements CommandLineRunner {
    @Autowired private InputChannelService fileWritingService;

    @Override
    public void run(String... args) {
        fileWritingService.sendMessageToInputChannel(getPayload("2", "msg20", "sender2"));
        fileWritingService.sendMessageToInputChannel(getPayload("2", "msg21", "sender2"));
        fileWritingService.sendMessageToInputChannel(getPayload("3", "msg30", "sender3"));
        fileWritingService.sendMessageToInputChannel(getPayload("3", "msg31", "sender3"));
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
