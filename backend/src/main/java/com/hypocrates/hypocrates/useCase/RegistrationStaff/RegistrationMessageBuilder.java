package com.hypocrates.hypocrates.useCase.RegistrationStaff;

import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true) @Setter
public class RegistrationMessageBuilder {
    private String username;
    private String token;
    private String clinicName;

    public String build() {
        return "Пользователь " + username + " зарегистрирован. Токен: " + token;
    }
}
