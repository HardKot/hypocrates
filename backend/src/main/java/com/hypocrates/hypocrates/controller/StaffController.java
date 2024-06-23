package com.hypocrates.hypocrates.controller;

import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import com.hypocrates.hypocrates.database.schema.StaffSchema;
import com.hypocrates.hypocrates.models.forms.CreateStaffForm;
import com.hypocrates.hypocrates.service.StaffService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Setter
@Controller
public class StaffController {
    @Autowired
    private StaffService staffService;

    @MutationMapping
public StaffSchema staff(@Argument(name = "form")CreateStaffForm form) {
        return staffService.createStaff(form).block();
    }
}
