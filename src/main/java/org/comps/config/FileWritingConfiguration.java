package org.comps.config;

import org.comps.model.ChatMessage;
import org.comps.service.FileWritingService;
import org.comps.service.InputChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageChannel;

import java.util.List;

@Configuration
public class FileWritingConfiguration {
    @Autowired private FileWritingService fileWritingService;

    @Bean("inputChannel")
    public MessageChannel fileChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow integrationFlow(@Qualifier("inputChannel") MessageChannel inputChannel) {
        return IntegrationFlows.from(inputChannel)
                .aggregate(agg -> agg.correlationStrategy(cs -> cs.getPayload() != null).groupTimeout(10000).sendPartialResultOnExpiry(true))
                .handle(message -> fileWritingService.writeMessagesToFile((List<ChatMessage>)message.getPayload()))
                .get();
    }
}
