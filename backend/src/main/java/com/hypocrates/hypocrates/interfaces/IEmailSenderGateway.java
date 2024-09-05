package com.hypocrates.hypocrates.interfaces;


public interface IEmailSenderGateway {
    IEmailSenderGateway email(String email);
    IEmailSenderGateway payload(String key, String payload);
    IEmailSenderGateway template(String template);
    boolean send();
}
