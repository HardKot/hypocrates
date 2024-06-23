package com.hypocrates.hypocrates.domain;

import lombok.Getter;


@Getter
public class StaffRole {
    private String name;

    private AppRule[] rules;

    public StaffRole(String name, AppRule[] rules) {
        this.name = name;
        this.rules = rules;
    }

    static public StaffRole Owner() {
        return new StaffRole("owner", AppRule.values());
    }
}
