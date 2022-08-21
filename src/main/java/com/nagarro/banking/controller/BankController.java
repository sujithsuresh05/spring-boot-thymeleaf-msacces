package com.nagarro.banking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bank")
public class BankController {

    @GetMapping("/dashboard")
    public String home(Model model) {
        return "/dashboard";
    }
}
