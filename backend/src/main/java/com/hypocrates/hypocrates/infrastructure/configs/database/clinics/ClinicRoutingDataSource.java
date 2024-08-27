package com.hypocrates.hypocrates.infrastructure.configs.database.clinics;

import com.hypocrates.hypocrates.infrastructure.context.ClinicContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class ClinicRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return ClinicContext.getClinicCode();
    }
}
