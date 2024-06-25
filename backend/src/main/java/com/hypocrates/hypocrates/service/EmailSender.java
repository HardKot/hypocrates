package com.hypocrates.hypocrates.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class EmailSender {
    private JavaMailSender mailSender;

    public void sendEmail(String email, String messageText) {
        var message = mailSender.createMimeMessage();

        var helper = new MimeMessageHelper(message);
        try {
            helper.setTo(email);
            helper.setText(messageText);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        mailSender.send(message);
    }
}
