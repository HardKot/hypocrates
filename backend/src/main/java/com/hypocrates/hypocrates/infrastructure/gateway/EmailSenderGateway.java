package com.hypocrates.hypocrates.infrastructure.gateway;

import com.hypocrates.hypocrates.interfaces.IEmailSenderFacade;
import com.hypocrates.hypocrates.interfaces.IEmailSenderGateway;
import com.hypocrates.hypocrates.interfaces.ITemplateBuilder;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class EmailSenderGateway implements IEmailSenderGateway {
    private final ITemplateBuilder templateBuilder;
    private final IEmailSenderFacade emailSenderFacade;
    private String email;

    @Override
    public IEmailSenderGateway email(String email) {
        this.email = email;
        return this;
    }

    @Override
    public IEmailSenderGateway payload(String key, String payload) {
        templateBuilder.payload(key, payload);
        return this;
    }

    @Override
    public IEmailSenderGateway template(String template) {
        templateBuilder.name(template);
        return this;
    }

    @Override
    public boolean send() {
        return emailSenderFacade.sendMail(email, templateBuilder.build());
    }
}
