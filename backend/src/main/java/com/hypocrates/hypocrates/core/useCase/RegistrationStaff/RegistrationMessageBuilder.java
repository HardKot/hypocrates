package com.hypocrates.hypocrates.core.useCase.RegistrationStaff;


public class RegistrationMessageBuilder {
    private String username;
    private String token;
    private String clinicName;

    public RegistrationMessageBuilder username(String username) {
        this.username = username;
        return this;
    }

    public RegistrationMessageBuilder token(String token) {
        this.token = token;
        return this;
    }

    public RegistrationMessageBuilder clinicName(String clinicName) {
        this.clinicName = clinicName;
        return this;
    }

    public String build() {
        return "Пользователь " + username + " зарегистрирован. Токен: " + token;
    }
}
