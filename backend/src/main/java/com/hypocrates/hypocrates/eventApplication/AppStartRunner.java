package com.hypocrates.hypocrates.eventApplication;

import com.hypocrates.hypocrates.core.useCase.ClinicInteract;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppStartRunner implements ApplicationRunner {
    private ClinicInteract clinicInteract;

    @Override
    public void run (ApplicationArguments args) throws Exception {
        clinicInteract.registerClinic();
    }
}
