package com.abidevel.oauth.authorization.model.request;

import com.abidevel.oauth.authorization.model.enumeration.GrantType;
import com.abidevel.oauth.authorization.model.enumeration.ScopeType;

import lombok.Data;

@Data
public class CreateClientRequest {
    private String username;
    private String secret;
    private String grantType;
    private String scopeType;

}
