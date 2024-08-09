package com.hypocrates.hypocrates.infrastructure.config.database.admin.schema;

import com.hypocrates.hypocrates.infrastructure.config.database.BaseSchema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity(name = "ConfirmedCode")
public class ConfirmedCodeSchema extends BaseSchema {
    private String code;

    @Column(name = "entity_id")
    private UUID entityID;

    public LocalDateTime getExpiration() {
        return createAt.plusMinutes(30);
    }
}