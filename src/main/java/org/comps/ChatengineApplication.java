package org.comps;

import org.comps.properties.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ChatengineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatengineApplication.class, args);
    }
}
