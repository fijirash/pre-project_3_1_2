package ru.fijirash.pp.spring_security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "/login/login-page";
    }

    @GetMapping("/welcome")
    public String getWelcomePage() {
        return "/login/welcome";
    }
}
