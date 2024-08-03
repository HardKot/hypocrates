package com.hypocrates.hypocrates.eventApplication;

import com.hypocrates.hypocrates.core.domain.appUser.User;
import com.hypocrates.hypocrates.core.useCase.ClinicInteract;
import com.hypocrates.hypocrates.database.clinics.schema.StaffSchema;
import com.hypocrates.hypocrates.service.ClinicService;
import com.hypocrates.hypocrates.service.StaffService;
import com.hypocrates.hypocrates.service.mapper.StaffMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppStartRunner implements ApplicationRunner {
    private ClinicInteract clinicInteract;
    private ClinicService clinicService;
    private StaffService staffService;
    private Environment environment;
    private ApplicationContext context;
    private final StaffMapper staffMapper;

    @Override
    public void run (ApplicationArguments args) throws Exception {
    }

}
