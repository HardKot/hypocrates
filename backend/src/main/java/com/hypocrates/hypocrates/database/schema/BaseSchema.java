package com.hypocrates.hypocrates.database.schema;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.util.Date;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BaseSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @LastModifiedDate
    @Column(name = "update_at")
    protected Date updateAt;

    @CreatedDate
    @Column(name = "create_at")
    protected Date createAt;
}
