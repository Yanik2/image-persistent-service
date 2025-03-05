package com.imageanalysis.persitentservice.config;

import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;

@Configuration
@IntegrationComponentScan
public class ProcessConfiguration {

    @Value("${pubsub.topic}")
    private String topic;

    @Bean
    @ServiceActivator(inputChannel = "pubsubOutputChannel")
    public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
        return new PubSubMessageHandler(pubsubTemplate, topic);
    }

    @Bean
    public MessageChannel pubsubOutputChannel() {
        return new DirectChannel();
    }
}
