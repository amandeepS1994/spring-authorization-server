package com.abidevel.oauth.authorization.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.abidevel.oauth.authorization.model.entity.Authority;
import com.abidevel.oauth.authorization.model.entity.User;
import com.abidevel.oauth.authorization.model.request.CreateUserRequest;
import com.abidevel.oauth.authorization.repository.AuthorityRepository;
import com.abidevel.oauth.authorization.repository.UserRepository;
import com.abidevel.oauth.authorization.service.UserService;
import com.abidevel.oauth.authorization.utility.UserUtility;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserServIceImplementation implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;


    public UserServIceImplementation(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }
   

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> createNewUser(CreateUserRequest user) {
        Optional<User> optionalCreatedUser = checkRequestedUserIsValid(user);
        return optionalCreatedUser.isPresent() ? persistUser(optionalCreatedUser.get()) : Optional.empty();
    }

    private Optional<User> checkRequestedUserIsValid (CreateUserRequest user) {
        if (Objects.nonNull(user) && checkUserValuesArePresent(user) && !userRepository.existsByUsername(user.getUsername()) && UserUtility.checkAuthorityIsValid(user.getAuthority())) {
            Optional<Authority> optionalAuth = authorityRepository.findByAuthorityName(user.getAuthority());
            return optionalAuth.isPresent() ? Optional.ofNullable(
                User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .build()
                .addAuthority(optionalAuth.get())
            ) : Optional.empty();
        }
        return Optional.empty();
    }

    private boolean checkUserValuesArePresent (CreateUserRequest user) {
        return (!user.getEmail().isEmpty() &&  !user.getUsername().isEmpty() && !user.getFirstName().isEmpty() && !user.getLastName().isEmpty() &&  !user.getPassword().isEmpty());
    }

    private boolean checkUserValuesArePresent (User user) {
        return (!user.getEmail().isEmpty() &&  !user.getUsername().isEmpty() && !user.getFirstName().isEmpty() && !user.getLastName().isEmpty() &&  !user.getPassword().isEmpty());
    }

    private Optional<User> persistUser (User user) {
        user.setCreatedAt(LocalDateTime.now());
        return Optional.ofNullable(userRepository.save(user));
    }


    @Override
    public Optional<User> retrieveUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public Optional<User> retreieveUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
}
