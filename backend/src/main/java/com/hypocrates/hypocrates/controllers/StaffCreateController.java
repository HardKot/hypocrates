package com.hypocrates.hypocrates.controllers;

import com.hypocrates.hypocrates.configs.database.clinics.schema.StaffSchema;
import com.hypocrates.hypocrates.application.dto.CreateStaffFullForm;
import com.hypocrates.hypocrates.application.dto.InviteStaffFullForm;
import com.hypocrates.hypocrates.application.services.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StaffCreateController {
    private final StaffService staffService;

    @MutationMapping
    public StaffSchema registration(@Argument CreateStaffFullForm form) {
        return staffService.createStaffByForm(form);
    }

    @MutationMapping
    public StaffSchema invite(@Argument InviteStaffFullForm form, @Argument StaffSchema staffSchema) {
        return staffService.createStaffByForm(form);
    }
}

