package com.abidevel.oauth.authorization.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OauthTest extends ControllerAbstractTests {

    @Test
    @DisplayName("Assumption is that both, the client and user exists in the database. Request is a " +
            "valid one, assert that the HTTP status is 200 OK and the authorization server " +
            "generates the access token.")
    void generateTokenValidUserAndClientTest() throws Exception {
  
    mockMvc.perform(
        post("/oauth/token")
        .with(httpBasic("client", "secret"))
        .queryParam("grant_type", "password")
        .queryParam("username", userConfiguration.getUsername())
        .queryParam("password", userConfiguration.getPassword())
        .queryParam("scope", "read")
    ).andExpect(jsonPath("$.access_token").exists())
    .andExpect(status().isOk());
 
  
    }
  
    @Test
    @DisplayName("Considering the client authenticating does not exist " +
            "assert that the response status is HTTP 4XX (client error) and " +
            "the authorization server doesn't generate the access token.")
    void generateTokenInvalidClientTest() throws Exception {
  
      mockMvc.perform(
        post("/oauth/token")
        .with(httpBasic("different_client", "secret"))
        .queryParam("grant_type", "password")
        .queryParam("username", userConfiguration.getUsername())
        .queryParam("password", userConfiguration.getPassword())
        .queryParam("scope", "read")
      ).andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.access_token").doesNotExist());
    }
  
    @Test
    @DisplayName("Considering the user authenticating the request does not exist " +
            "assert that the response status is HTTP 4XX (client error) and " +
            "the authorization server doesn't generate the access token.")
    void generateTokenInvalidUserTest() throws Exception {
  
      mockMvc.perform(
              post("/oauth/token")
                      .with(httpBasic("client", "secret"))
                      .queryParam("grant_type", "password")
                      .queryParam("username", "other_user")
                      .queryParam("password", "password")
                      .queryParam("scope", "read")
      )
              .andExpect(status().is4xxClientError())
              .andExpect(jsonPath("$.access_token").doesNotExist());
  
    }
  
    @Test
    @DisplayName("Considering the password for either client or user is not valid " +
            "assert that the response status is HTTP 4XX (client error) and " +
            "the authorization server doesn't generate the access token.")
    void generateTokenPasswordNotValidTest() throws Exception {
  
      mockMvc.perform(
              post("/oauth/token")
                      .with(httpBasic("client", "other_secret"))
                      .queryParam("grant_type", "password")
                      .queryParam("username", userConfiguration.getUsername())
                      .queryParam("password", userConfiguration.getPassword())
                      .queryParam("scope", "read")
      )
              .andExpect(status().is4xxClientError())
              .andExpect(jsonPath("$.access_token").doesNotExist());
  
    }
  
    @Test
    @DisplayName("Considering the request is valid and the client has the refresh_token grant type " +
            "assert that the authorization server issues both the access token and the refresh token.")
    void generateRefreshTokenTest() throws Exception {
  
      mockMvc.perform(
              post("/oauth/token")
                      .with(httpBasic("client", "secret"))
                      .queryParam("grant_type", "password")
                      .queryParam("username", userConfiguration.getUsername())
                      .queryParam("password", userConfiguration.getPassword())
                      .queryParam("scope", "read")
      )
              .andExpect(jsonPath("$.access_token").exists())
              .andExpect(jsonPath("$.refresh_token").exists())
              .andExpect(status().isOk());
  
    }

}
