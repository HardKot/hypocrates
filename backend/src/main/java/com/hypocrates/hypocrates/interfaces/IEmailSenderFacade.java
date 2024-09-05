package com.hypocrates.hypocrates.interfaces;

public interface IEmailSenderFacade {
    boolean sendMail(String email, String body);
}
