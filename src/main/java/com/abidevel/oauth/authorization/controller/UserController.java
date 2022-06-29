package com.abidevel.oauth.authorization.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abidevel.oauth.authorization.model.DTO.UserDTO;
import com.abidevel.oauth.authorization.model.entity.User;
import com.abidevel.oauth.authorization.model.request.CreateUserRequest;
import com.abidevel.oauth.authorization.model.response.ApiResponse;
import com.abidevel.oauth.authorization.service.UserService;
import com.abidevel.oauth.authorization.utility.ObjectMapperUtility;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(value = "/user/")
public class UserController {
    
    private final UserService userService;
    private final HttpServletRequest request;


    public UserController(UserService userService, HttpServletRequest request) {
        this.userService = userService;
        this.request = request;
    }
    
    @GetMapping(value="")
    public ResponseEntity<ApiResponse<Object>> retrieveAllUsers() {
        List<User> allUser = userService.findAllUsers();
        return allUser.isEmpty() ? new ResponseEntity<>(
            ApiResponse.builder()
                .message("No Users Found.")
                .isSuccessful(false)
                .responseTime(LocalDateTime.now())
                .path(request.getRequestURI())
                .build()
            , HttpStatus.NOT_FOUND) : new ResponseEntity<>(
                ApiResponse.builder()
                    .message("All Users.")
                    .isSuccessful(true)
                    .data(ObjectMapperUtility.mapAllEntities(allUser, UserDTO.class))
                    .responseTime(LocalDateTime.now())
                    .path(request.getRequestURI())
                    .build(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<Object>> createUser (@RequestBody CreateUserRequest entity) {
        Optional<User> createdUser = userService.createNewUser(entity);
        return createdUser.isPresent() ? 
            new ResponseEntity<>(ApiResponse.builder()
                .message("User Created.")
                .isSuccessful(true)
                .path(request.getRequestURI())
                .responseTime(LocalDateTime.now())
                .data(ObjectMapperUtility.mapEntity(createdUser.get(), UserDTO.class))
                .build(), HttpStatus.CREATED) :
            new ResponseEntity<>(ApiResponse.builder()
                .message("Failed To Create User.")
                .isSuccessful(false)
                .path(request.getRequestURI())
                .responseTime(LocalDateTime.now())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        
    }
    
    
}
