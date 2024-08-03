package com.hypocrates.hypocrates.database.schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "token_schema")
public class TokenSchema {
    @Id
    private int id;

    private String token;

    @CreatedDate
    @Column(name = "create_at")
    protected Date createAt = new Date();

}
