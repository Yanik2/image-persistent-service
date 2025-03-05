package com.imageanalysis.persitentservice.service;

import com.imageanalysis.persitentservice.dto.PubSubMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PubSubService {
    private final PubSubGateway pubSubGateway;

    public void sendMessage(PubSubMessage message) {
        log.info("Publishing event: {}", message);
        pubSubGateway.sendToPubSub(message);
    }

}
