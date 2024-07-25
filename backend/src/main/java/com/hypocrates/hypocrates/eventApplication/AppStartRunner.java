package com.hypocrates.hypocrates.eventApplication;

import com.hypocrates.hypocrates.core.domain.appUser.User;
import com.hypocrates.hypocrates.core.domain.staff.Staff;
import com.hypocrates.hypocrates.core.useCase.ClinicInteract;
import com.hypocrates.hypocrates.database.schema.StaffSchema;
import com.hypocrates.hypocrates.service.ClinicService;
import com.hypocrates.hypocrates.service.StaffService;
import com.hypocrates.hypocrates.service.mapper.StaffMapper;
import com.leakyabstractions.result.core.Results;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
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
        var clinic = clinicService.getClinic();
        var ownerSchema = staffService.getOwner();
        if (ownerSchema == null) { ownerSchema = new StaffSchema(); }
        var ownerEmail = environment.getProperty("OWNER_EMAIL");

        var owner = staffMapper.toEntity(ownerSchema);

        var user = User.builder().email(ownerEmail).build();

        if (clinic.isNew() && owner.getId() == null) {
            clinicInteract.registtationlinic(user, clinic);
        } else if (clinic.isNew() && !owner.getIsActive()) {
            clinicInteract.activateClinic(owner, clinic);
        }
    }

}
