package com.abidevel.oauth.authorization.configuration.authentication;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.abidevel.oauth.authorization.model.entity.User;
import com.abidevel.oauth.authorization.service.UserService;

@Configuration(value = "byUsername")
public class UserDetailServiceByUsername implements UserDetailsService {

    private final UserService userService;

    public UserDetailServiceByUsername (UserService uService) {
        this.userService = uService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.userService.retrieveUserByUsername(username);
        if (optionalUser.isPresent()) {
            return new UserDetail(optionalUser.get());
        }
        throw new UsernameNotFoundException(username);
    }
    
}
