package com.abidevel.oauth.authorization.configuration.authentication;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.abidevel.oauth.authorization.model.entity.User;
import com.abidevel.oauth.authorization.service.UserService;

@Configuration(value = "byEmail")
public class UserDetailServiceByEmail implements UserDetailsService {

    private final UserService userService;

    public UserDetailServiceByEmail (UserService uService) {
        this.userService = uService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.userService.retreieveUserByEmail(email);
        if (optionalUser.isPresent()) {
            return new UserDetail(optionalUser.get());
        }
        throw new UsernameNotFoundException(email);
    }
    
}
