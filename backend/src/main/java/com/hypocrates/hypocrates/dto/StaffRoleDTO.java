package com.hypocrates.hypocrates.dto;

import com.hypocrates.hypocrates.domain.staff.AppRule;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StaffRoleDTO {
    private Long id;
    private String name;
    private AppRule[] rules;
}
