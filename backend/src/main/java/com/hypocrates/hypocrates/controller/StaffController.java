package com.hypocrates.hypocrates.controller;

import com.hypocrates.hypocrates.database.schema.StaffSchema;
import com.hypocrates.hypocrates.models.forms.CreateStaffForm;
import com.hypocrates.hypocrates.service.StaffService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Setter
@Controller
@AllArgsConstructor
public class StaffController {
    private StaffService staffService;

    @MutationMapping
    public StaffSchema createStaff(@Argument(name = "form")CreateStaffForm form) {
//        return staffService.createStaff(form);
        return null;
    }
}
