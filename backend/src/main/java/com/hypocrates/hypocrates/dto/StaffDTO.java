package com.hypocrates.hypocrates.dto;

import lombok.Data;


import java.util.Date;

@Data
public class StaffDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String patronymic;

    private Date birthday;

    private String email;
    private String phone;

    private String avatarUrl;
    private String password;

    private Long clinicId;

    private Long roleId;

    private boolean phoneIsActive;
    private boolean emailIsActive;
}
