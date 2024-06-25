package com.hypocrates.hypocrates.dto;

import com.hypocrates.hypocrates.domain.AppRule;
import lombok.Data;

@Data
public class StaffRoleDTO {
    private Long id;
    private String name;
    private AppRule[] rules;
}
