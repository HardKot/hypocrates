package com.hypocrates.hypocrates.infrastructure.config.database;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    protected Date updateAt = new Date();

    @CreatedDate
    @Column(name = "create_at")
    protected Date createAt = new Date();
}
