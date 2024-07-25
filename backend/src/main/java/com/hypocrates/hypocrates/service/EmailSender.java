package com.hypocrates.hypocrates.service;

import freemarker.template.Configuration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class EmailSender {
    private JavaMailSender mailSender;
    private Configuration configuration = new Configuration(Configuration.VERSION_2_3_33);



    public void sendEmail(String email, String templateName, Map<String, String> model) {
        var mimeMessage = mailSender.createMimeMessage();
        try {
            var template  = configuration.getTemplate(templateName);
            var message = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            var helper = new MimeMessageHelper(mimeMessage);
            helper.setTo(email);
            helper.setText(message);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        mailSender.send(mimeMessage);
    }
}
