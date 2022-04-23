package org.comps.config;

import org.comps.model.ChatMessages;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.DefaultFileNameGenerator;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.xml.transformer.MarshallingTransformer;
import org.springframework.integration.xml.transformer.ResultToStringTransformer;
import org.springframework.integration.xml.transformer.ResultTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.transform.OutputKeys;
import java.io.File;
import java.util.Properties;

@Configuration
public class FileWritingConfiguration {

    @Bean("inputChannel")
    public MessageChannel fileChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow integrationFlow(@Qualifier("inputChannel") MessageChannel fileChannel, FileWritingMessageHandler fileWriter) {
        return IntegrationFlows.from(fileChannel)
                .transform(marshallingTransformer())
                .channel("appendToFile")
                .handle(fileWriter).get();
    }

    @Bean
    public MarshallingTransformer marshallingTransformer() {
        return new MarshallingTransformer(getMarshaller(), resultTransformer());
    }

    @Bean
    public FileWritingMessageHandler fileWriter(@Value("${msgs.dir:output-xmls}") String msgsDir) {
        FileWritingMessageHandler writer = new FileWritingMessageHandler(new File(msgsDir));
        writer.setExpectReply(false);
        writer.setFileExistsMode(FileExistsMode.APPEND_NO_FLUSH);
        writer.setAppendNewLine(true);
        writer.setFileNameGenerator(new DefaultFileNameGenerator());
        return writer;
    }

    @Bean
    public Marshaller getMarshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(ChatMessages.class);
        return marshaller;
    }

    @Bean
    public ResultTransformer resultTransformer() {
        ResultToStringTransformer resultToStringTransformer= new ResultToStringTransformer();
        Properties p = new Properties();
        p.setProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        resultToStringTransformer.setOutputProperties(p);
        return resultToStringTransformer;
    }
}
