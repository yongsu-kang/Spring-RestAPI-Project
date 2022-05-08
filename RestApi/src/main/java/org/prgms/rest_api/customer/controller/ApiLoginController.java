package org.prgms.rest_api.customer.controller;

import org.prgms.rest_api.customer.service.LoginService;
import org.prgms.rest_api.vo.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/login")
public class ApiLoginController {

    private final LoginService loginService;

    public ApiLoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public ResponseEntity login(@Valid @RequestParam Email email) {
        loginService.login(email);
        return ResponseEntity.ok().build();
    }
}
