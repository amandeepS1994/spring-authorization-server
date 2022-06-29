package com.abidevel.oauth.authorization.service;

import java.util.List;
import java.util.Optional;

import com.abidevel.oauth.authorization.model.entity.User;
import com.abidevel.oauth.authorization.model.request.CreateUserRequest;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> createNewUser(CreateUserRequest user);
}
