package com.hypocrates.hypocrates.infrastructure.config.database.clinics;

import com.hypocrates.hypocrates.infrastructure.ClinicContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;


public class ClinicRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return ClinicContext.getClinicCode();
    }
}
