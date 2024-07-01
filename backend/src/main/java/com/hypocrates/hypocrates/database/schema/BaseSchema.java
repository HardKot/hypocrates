package com.hypocrates.hypocrates.database.schema;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @LastModifiedDate
    protected Date updateAt;

    @CreatedDate
    protected Date createAt;
}
