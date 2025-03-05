package com.imageanalysis.persitentservice.dto;

import com.imageanalysis.persitentservice.model.ProcessStatus;
import java.util.UUID;

public record UpdateStatusDto(
    ProcessStatus status,
    UUID processingId
) {

}
