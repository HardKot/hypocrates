package com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema;

import com.hypocrates.hypocrates.infrastructure.config.database.BaseSchema;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity(name = "Token")
@SuperBuilder
@NoArgsConstructor
public class TokenSchema extends BaseSchema {
    private String token;
}
