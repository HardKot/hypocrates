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

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ConfirmedCode")
public class ConfirmedCodeSchema extends BaseSchema {
    private String code;

}