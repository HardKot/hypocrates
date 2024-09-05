package com.hypocrates.hypocrates.infrastructure.facade;

import com.hypocrates.hypocrates.interfaces.ITemplateBuilder;
import com.hypocrates.hypocrates.interfaces.ITemplateFacade;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
@AllArgsConstructor
public class TemplateFacade implements ITemplateFacade {
    private Configuration configuration;

    @Override
    public String getBody(String templateName, Map<String, String> model) throws IOException, TemplateException {
        var template = configuration.getTemplate(templateName + ".ftl");
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }

    @Override
    public String getBody(String templateName) throws IOException, TemplateException {
        return getBody(templateName, Map.of());
    }

    @Override
    public ITemplateBuilder templateBuilder() {
        return new TemplateBuilder((String name) -> {
            try {
                return configuration.getTemplate(name + ".ftl");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    static class TemplateBuilder implements ITemplateBuilder {
        private final Map<String, String> model = new HashMap<>();
        private final Function<String, Template> getTemplate;
        private Template template;

        public TemplateBuilder(Function<String, Template> getTemplate) {
            this.getTemplate = getTemplate;
        }

        @Override
        public ITemplateBuilder name(String name) {
            template = getTemplate.apply(name);
            return this;
        }

        @Override
        public ITemplateBuilder payload(String key, String payload) {
            model.put(key, payload);
            return this;
        }

        @Override
        public String build() {
            try {
                return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            } catch (TemplateException | IOException e) {
                log.error(e.getMessage());
                return "";
            }
        }
    }
}
