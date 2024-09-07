package com.hypocrates.hypocrates.controllers;

import com.hypocrates.hypocrates.application.services.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final StaffService staffService;


    @GetMapping("/")
    public String login() {
        return "StaffLogin";
    }

    @GetMapping("/registration")
    public String registration() {
        return "StaffRegistration";
    }

}
