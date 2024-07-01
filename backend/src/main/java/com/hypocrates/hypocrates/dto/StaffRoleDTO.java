package com.hypocrates.hypocrates.dto;

import com.hypocrates.hypocrates.domain.staff.AppRule;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class StaffRoleDTO {
    private UUID id;
    private String name;
    private AppRule[] rules;
}
