package com.imageanalysis.persitentservice.service;

import com.imageanalysis.persitentservice.dto.PubSubMessage;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingGateway(defaultRequestChannel = "pubsubOutputChannel")
public interface PubSubGateway {
    void sendToPubSub(@Payload PubSubMessage message);
}