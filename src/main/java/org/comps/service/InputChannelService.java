package org.comps.service;

import org.comps.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public class InputChannelService {
    @Autowired @Qualifier("inputChannel")
    private MessageChannel inputChannel;

    public void sendMessageToInputChannel(ChatMessage msg) {
        Message<ChatMessage> message = MessageBuilder.withPayload(msg).build();
        inputChannel.send(message);
    }
}
