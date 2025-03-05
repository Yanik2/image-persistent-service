package com.imageanalysis.persitentservice.repository;

import com.imageanalysis.persitentservice.model.ProcessEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRepository extends JpaRepository<ProcessEntity, UUID> {
}
