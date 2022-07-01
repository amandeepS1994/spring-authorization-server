package com.abidevel.oauth.authorization.configuration.authentication.user;

import org.springframework.security.core.GrantedAuthority;

import com.abidevel.oauth.authorization.model.enumeration.AuthorityTypes;

public final class UserGrantedAuthority implements GrantedAuthority{
    private final AuthorityTypes role;

    public UserGrantedAuthority (AuthorityTypes role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.name();
    }
    
}
