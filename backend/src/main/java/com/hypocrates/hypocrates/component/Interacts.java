package com.hypocrates.hypocrates.component;

import com.hypocrates.hypocrates.core.domain.appUser.IUserGateway;
import com.hypocrates.hypocrates.core.domain.clinic.IClinicGateway;
import com.hypocrates.hypocrates.core.domain.staff.IStaffGateway;
import com.hypocrates.hypocrates.core.useCase.ClinicInteract;
import com.hypocrates.hypocrates.gateway.ClinicGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Interacts {


    @Bean
    public ClinicInteract clinicInteract(IClinicGateway clinicGateway, IStaffGateway staffGateway, IUserGateway userGateway) {
        return new ClinicInteract(clinicGateway, staffGateway, userGateway) ;
    }
}
