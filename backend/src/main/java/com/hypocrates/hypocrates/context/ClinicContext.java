package com.hypocrates.hypocrates.context;

import com.hypocrates.hypocrates.database.schema.ClinicSchema;

public class ClinicContext {
    static private ThreadLocal<ClinicSchema> clinic = new ThreadLocal<>();

    static public ClinicSchema get() {
        return clinic.get();
    }
}
