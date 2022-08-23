package com.nagarro.banking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {


    @RequestMapping("/login")
    public String loginLandingPage(Model model) {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping("/login-session-invalid")
    public String loginSessionInvalid(Model model) {
        model.addAttribute("loginSessionInvalid", true);
        return "login";
    }

}
