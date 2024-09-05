package com.hypocrates.hypocrates.interfaces;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

public interface ITemplateFacade {
    String getBody(String templateName, Map<String, String> model) throws IOException, TemplateException;

    String getBody(String templateName) throws IOException, TemplateException;

    ITemplateBuilder templateBuilder() throws IOException;
}
