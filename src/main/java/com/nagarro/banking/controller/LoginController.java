package com.nagarro.banking.controller;

import com.nagarro.banking.utils.ModelAtributeConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String loginLandingPage(Model model) {
        return ModelAtributeConstant.LOGIN_VIEW;
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute(ModelAtributeConstant.LOGIN_ERROR, true);
        return ModelAtributeConstant.LOGIN_VIEW;
    }

    @GetMapping("/login-session-invalid")
    public String loginSessionInvalid(Model model) {
        model.addAttribute(ModelAtributeConstant.LOGIN_SESSION_INVALID, true);
        return ModelAtributeConstant.LOGIN_VIEW;
    }

}
