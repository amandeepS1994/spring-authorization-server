package com.abidevel.oauth.authorization.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abidevel.oauth.authorization.model.DTO.ClientDTO;
import com.abidevel.oauth.authorization.model.entity.Client;
import com.abidevel.oauth.authorization.model.request.CreateClientRequest;
import com.abidevel.oauth.authorization.model.response.ApiResponse;
import com.abidevel.oauth.authorization.service.ClientService;
import com.abidevel.oauth.authorization.utility.ObjectMapperUtility;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/client/")
public class ClientController {
    private final ClientService clientService;
    private final HttpServletRequest request;

    public ClientController(ClientService clientService, HttpServletRequest request) {
        this.clientService = clientService;
        this.request = request;
    }

    @GetMapping(value="")
    public ResponseEntity<ApiResponse<Object>> retrieveAllClients() {
        List<Client>clients = clientService.retrieveAllClients();
        return clients.isEmpty() ?
        new ResponseEntity<>(ApiResponse.builder()
            .isSuccessful(true)
            .responseTime(LocalDateTime.now())
            .message("No Clients Found.")
            .path(request.getRequestURI())
            .build()
        , HttpStatus.OK) :
        new ResponseEntity<>(ApiResponse.builder()
            .isSuccessful(true)
            .responseTime(LocalDateTime.now())
            .message("List of Clients")
            .path(request.getRequestURI())
            .data(ObjectMapperUtility.mapAllEntities(clients, ClientDTO.class))
            .build()
        , HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<Object>> createClient (@RequestBody CreateClientRequest clientRequest) {
        Optional<Client> optionalClient = clientService.createClient(clientRequest);
        return !optionalClient.isPresent() ?
        new ResponseEntity<>(ApiResponse.builder()
            .isSuccessful(false)
            .responseTime(LocalDateTime.now())
            .message("Failed to Create Client.")
            .path(request.getRequestURI())
            .build()
        , HttpStatus.INTERNAL_SERVER_ERROR) :
        new ResponseEntity<>(ApiResponse.builder()
            .isSuccessful(true)
            .responseTime(LocalDateTime.now())
            .message("Client Created,")
            .path(request.getRequestURI())
            .data(ObjectMapperUtility.mapEntity(optionalClient.get(), ClientDTO.class))
            .build()
        , HttpStatus.CREATED);
    }
    
}
