package com.hypocrates.hypocrates.database.schema;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseSchema {
    @Id
    private Long id;

    @LastModifiedDate
    private Date updateAt;

    @CreatedDate
    private Date createAt;
}
