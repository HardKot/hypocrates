package com.hypocrates.hypocrates.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.UUID;


@Getter
@EqualsAndHashCode
@SuperBuilder
public abstract class AbstractEntity {
    protected UUID id;
    protected Date updateAt;
    protected Date createAt;

}
