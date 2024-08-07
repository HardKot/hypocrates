package com.hypocrates.hypocrates.infrastructure.config.database.admin.schema;

import com.hypocrates.hypocrates.infrastructure.config.database.BaseSchema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity(name = "Token")
@SuperBuilder
@NoArgsConstructor
public class TokenSchema extends BaseSchema {
    private String token;
}
