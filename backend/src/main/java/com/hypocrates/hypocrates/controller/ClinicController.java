package com.hypocrates.hypocrates.controller;

import com.hypocrates.hypocrates.database.schema.ClinicSchema;
import com.hypocrates.hypocrates.service.ClinicService;
import lombok.AllArgsConstructor;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ClinicController {
    private ClinicService clinicService;


    @QueryMapping
    public ClinicSchema clinic(@Argument Long id) {
        return clinicService.getById(id);
    }

}
