package com.hypocrates.hypocrates.infrastructure.config.database;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @LastModifiedDate
    @Column(name = "update_at")
    protected LocalDateTime updateAt = LocalDateTime.now();

    @CreatedDate
    @Column(name = "create_at")
    protected LocalDateTime createAt = LocalDateTime.now();
}
