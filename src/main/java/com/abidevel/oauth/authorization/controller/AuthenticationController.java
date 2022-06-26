package com.abidevel.oauth.authorization.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(path = "/authentication/")
public class AuthenticationController {
    @GetMapping("")
    public String getMethodName() {
        return "Endpoint testing.";
    }
    
}
