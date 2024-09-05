package com.hypocrates.hypocrates.interfaces;


public interface ITemplateBuilder {
    ITemplateBuilder name(String name);
    ITemplateBuilder payload(String key, String payload);

    String build();
}
