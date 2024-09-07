package com.hypocrates.hypocrates.domain.clinicManagement.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractClinicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @LastModifiedDate
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @CreatedDate
    @Column(name = "create_at")
    private LocalDateTime createAt;
}
