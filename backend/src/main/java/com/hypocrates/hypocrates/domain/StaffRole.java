package com.hypocrates.hypocrates.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class StaffRole {
    private Long id;
    private String name;

    private AppRule[] rules;

    public StaffRole(Long id, String name, AppRule[] rules) {
        this.id = id;
        this.name = name;
        this.rules = rules;
    }

    static public StaffRole Owner() {
        return new StaffRole(0L, "owner", AppRule.values());
    }
}
