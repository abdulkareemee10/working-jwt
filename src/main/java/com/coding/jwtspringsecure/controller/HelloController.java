package com.coding.jwtspringsecure.controller;

import com.coding.jwtspringsecure.userLoginSession.LoginDetailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @Autowired
    private LoginDetailService loginDetailService;
    @GetMapping("/hello")
    public String hello() {
        return "HELLO FROM JWT AUTHENTICATION";
    }

    @GetMapping("/login-details")
    public String loginDetails(HttpServletRequest request) {
        loginDetailService.printLoginDetails(request);
        return "Login details printed in the console";
    }
}