package com.abidevel.oauth.authorization.service;

import java.util.List;
import java.util.Optional;

import com.abidevel.oauth.authorization.model.entity.Client;
import com.abidevel.oauth.authorization.model.request.CreateClientRequest;

public interface ClientService {
    List<Client> retrieveAllClients();
    Optional<Client> createClient(CreateClientRequest createClient);
    Optional<Client> findClientById (int clientId);
    Optional<Client> findClientById (Long clientId);
    Optional<Client> findClientByUsername (String username);
    Optional<Client> findByClientById(String clientId);
}
