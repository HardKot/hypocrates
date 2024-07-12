package com.hypocrates.hypocrates.database.schema;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@Entity
public class ConfigSchema {
    @Id
    private Integer id;

    private String name;
    private String value;

    @LastModifiedDate
    private Date updateAt;

    @CreatedDate
    private Date createAt;


}