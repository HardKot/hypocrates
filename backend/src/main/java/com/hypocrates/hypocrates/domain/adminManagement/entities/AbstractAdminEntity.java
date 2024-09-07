package com.hypocrates.hypocrates.domain.adminManagement.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractAdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @LastModifiedDate
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @CreatedDate
    @Column(name = "create_at")
    private LocalDateTime createAt;
}
