package com.hypocrates.hypocrates.infrastructure.facade;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class TemplateFacade {
    private Configuration configuration;


    @PostConstruct
    public void loadedTemplate() {
    }

    public String getBody(String templateName, Map<String, String> model) throws IOException, TemplateException {
        var template = configuration.getTemplate(templateName + ".ftl");
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }

    public String getBody(String templateName) throws IOException, TemplateException {
        return getBody(templateName, Map.of());
    }
}
