package com.hypocrates.hypocrates.infrastructure.component;

import com.hypocrates.hypocrates.entity.user.IUserGateway;
import com.hypocrates.hypocrates.entity.clinic.IClinicGateway;
import com.hypocrates.hypocrates.entity.staff.IStaffGateway;
import com.hypocrates.hypocrates.useCase.clinicInteract.ClinicInteract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Interacts {
    @Bean
    public ClinicInteract clinicInteract(IClinicGateway clinicGateway, IStaffGateway staffGateway) {
        return new ClinicInteract(clinicGateway, staffGateway) ;
    }
}
