package com.imageanalysis.persitentservice.dto;

import java.util.UUID;

public record ConfirmationResponseDto(
    UUID imageProcessId
) {
}
