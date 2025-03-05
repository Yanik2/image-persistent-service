package com.imageanalysis.persitentservice.dto;

import java.util.UUID;

public record PubSubMessage(
    String imageName,
    UUID processId
) {
}
