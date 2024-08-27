package com.hypocrates.hypocrates.infrastructure.facade;

import freemarker.template.Configuration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class EmailSenderFacade {
    private JavaMailSender mailSender;
    private Configuration configuration = new Configuration(Configuration.VERSION_2_3_33);

    public void sendEmail(String email, String body) {
        var mimeMessage = mailSender.createMimeMessage();
        try {
            var message = new MimeMessageHelper(mimeMessage);
            message.setTo(email);
            message.setText(body);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}