package com.hypocrates.hypocrates.application.useCase.clinicInteract.dto;

public interface IClinicRegistration {
    String name();
    String clinicEmail();
    String staffEmail();
    String password();
    String confirmPassword();
}
