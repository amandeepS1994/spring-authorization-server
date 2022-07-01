package com.abidevel.oauth.authorization.configuration.authentication.user;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.abidevel.oauth.authorization.model.entity.Authority;
import com.abidevel.oauth.authorization.model.entity.User;

public class UserDetail implements UserDetails {
    private final transient User user;

    public UserDetail (User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities().stream()
            .map(Authority::getAuthorityName)
            .map(UserGrantedAuthority::new)
            .collect(Collectors.toList());
      
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
