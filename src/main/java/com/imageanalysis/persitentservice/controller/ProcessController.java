package com.imageanalysis.persitentservice.controller;

import com.imageanalysis.persitentservice.dto.ConfirmationResponseDto;
import com.imageanalysis.persitentservice.dto.CreateProcessRequest;
import com.imageanalysis.persitentservice.dto.CreateProcessResponse;
import com.imageanalysis.persitentservice.dto.ImageProcessStatusDto;
import com.imageanalysis.persitentservice.dto.UpdateStatusDto;
import com.imageanalysis.persitentservice.service.ProcessService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process")
@RequiredArgsConstructor
@Slf4j
public class ProcessController {
    private final ProcessService processService;

    @PostMapping
    public ResponseEntity<CreateProcessResponse> createProcess(
        @RequestBody CreateProcessRequest request
    ) {
        log.info("Received request for createProcess, {}", request);
        return new ResponseEntity<>(processService.createProcess(request), HttpStatus.CREATED);
    }

    @PutMapping("/{processId}/confirm")
    public ResponseEntity<ConfirmationResponseDto> confirm(
        @PathVariable UUID processId
    ) {
        log.info("Received request for confirm upload, {}", processId);
        return ResponseEntity.ok(processService.confirm(processId));
    }

    @GetMapping("/{processId}/status")
    public ResponseEntity<ImageProcessStatusDto> getStatus(@PathVariable UUID processId) {
        log.info("Received request for get status, {}", processId);
        return ResponseEntity.ok(processService.getStatus(processId));
    }

    @PutMapping("/{processId}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID processId,
                                             @RequestBody UpdateStatusDto request) {
        log.info("Received request for update status, {}", request);
        processService.updateStatus(request, processId);
        return ResponseEntity.noContent().build();
    }
}
