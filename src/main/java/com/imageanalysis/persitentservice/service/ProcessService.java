package com.imageanalysis.persitentservice.service;

import com.imageanalysis.persitentservice.dto.ConfirmationResponseDto;
import com.imageanalysis.persitentservice.dto.CreateProcessRequest;
import com.imageanalysis.persitentservice.dto.CreateProcessResponse;
import com.imageanalysis.persitentservice.dto.ImageProcessStatusDto;
import com.imageanalysis.persitentservice.dto.PubSubMessage;
import com.imageanalysis.persitentservice.dto.UpdateStatusDto;
import com.imageanalysis.persitentservice.model.ProcessEntity;
import com.imageanalysis.persitentservice.model.ProcessStatus;
import com.imageanalysis.persitentservice.repository.ProcessRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProcessService {
    private final ProcessRepository processRepository;
    private final PubSubService pubSubService;

    @Transactional
    public CreateProcessResponse createProcess(CreateProcessRequest request) {
        final var process = new ProcessEntity();
        process.setStatus(ProcessStatus.CREATED);
        process.setImageName(request.imageName());
        processRepository.saveAndFlush(process);
        log.info("Saved process in db, {}", process);
        return new CreateProcessResponse(process.getId());
    }

    @Transactional
    public ConfirmationResponseDto confirm(UUID processId) {
        final var process = processRepository.findById(processId)
            .orElseThrow();
        process.setStatus(ProcessStatus.IN_PROGRESS);

        pubSubService.sendMessage(new PubSubMessage(process.getImageName(), process.getId()));
        processRepository.save(process);
        log.info("Confirmed process: {}", process);
        return new ConfirmationResponseDto(process.getId());
    }

    public ImageProcessStatusDto getStatus(UUID processId) {
        final var process = processRepository.findById(processId).orElseThrow();
        log.info("Fetched process from db: {}", process);
        return new ImageProcessStatusDto(process.getId(), process.getStatus());
    }

    @Transactional
    public void updateStatus(UpdateStatusDto request, UUID processId) {
        final var process = processRepository.findById(processId).orElseThrow();
        log.info("Updating status, old: {}, new: {}", process.getStatus(), request.status());
        process.setStatus(request.status());
        processRepository.save(process);
    }
}
