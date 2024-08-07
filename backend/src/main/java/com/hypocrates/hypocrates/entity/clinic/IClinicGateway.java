package com.hypocrates.hypocrates.entity.clinic;


import freemarker.template.TemplateException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public interface IClinicGateway {
    ClinicModel getClinicByEmail(String email);

    boolean isExistingClinic(String email);

    String sendActiveEmail(ClinicModel clinic) throws TemplateException, IOException;

    String getEmptyClinicCode();

    ClinicModel createClinic(ClinicModel clinic);
}
