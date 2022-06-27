package com.abidevel.oauth.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.abidevel.oauth.authorization.AuthorizationApplicationTests;
import com.abidevel.oauth.authorization.configuration.UserConfiguration;

@AutoConfigureMockMvc
abstract class ControllerAbstractTests extends AuthorizationApplicationTests {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected UserConfiguration userConfiguration;
    
}
