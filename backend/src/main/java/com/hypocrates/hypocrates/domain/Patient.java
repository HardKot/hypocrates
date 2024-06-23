package com.hypocrates.hypocrates.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Patient extends User {
    private Clinic clinic;
}
