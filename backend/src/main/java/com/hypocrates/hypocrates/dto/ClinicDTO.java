package com.hypocrates.hypocrates.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClinicDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String avatarUrl;

}

