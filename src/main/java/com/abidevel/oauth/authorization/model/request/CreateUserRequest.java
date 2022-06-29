package com.abidevel.oauth.authorization.model.request;

import com.abidevel.oauth.authorization.model.enumeration.AuthorityTypes;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private AuthorityTypes authority;
}
