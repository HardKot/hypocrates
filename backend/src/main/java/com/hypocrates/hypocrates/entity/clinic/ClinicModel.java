package com.hypocrates.hypocrates.entity.clinic;


import com.hypocrates.hypocrates.entity.AbstractEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ClinicModel extends AbstractEntity {
    private String code;
    private String name;
    private String address;
    private String avatarUrl;
    private String email;

    private ClinicStatus status;

    public boolean isNew() {
        return status == ClinicStatus.NO_ACTIVE;
    }
}
