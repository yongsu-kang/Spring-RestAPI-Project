package org.prgms.rest_api.customer.controller;

import org.prgms.rest_api.customer.dto.CustomerDto;
import org.prgms.rest_api.customer.service.LoginService;
import org.prgms.rest_api.vo.Email;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String login(@Valid Email email) {
        CustomerDto login = loginService.login(email);
        return "redirect:/products/" + login.getCustomerId();
    }
}
