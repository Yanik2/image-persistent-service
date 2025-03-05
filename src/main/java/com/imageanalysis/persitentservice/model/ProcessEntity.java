package com.imageanalysis.persitentservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "process")
@Data
public class ProcessEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @UuidGenerator
    private UUID id;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProcessStatus status;
}
