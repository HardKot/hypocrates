package com.hypocrates.hypocrates.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {

    @GetMapping("/activate/{activateToken}")
    public String activity(@PathVariable String activateToken) {

        return  "ok";
    }

}
