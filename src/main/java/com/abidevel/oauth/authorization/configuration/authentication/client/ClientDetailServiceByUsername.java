package com.abidevel.oauth.authorization.configuration.authentication.client;

import java.util.Optional;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.abidevel.oauth.authorization.model.entity.Client;
import com.abidevel.oauth.authorization.service.ClientService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service(value = "clientDetailByUsername")
public class ClientDetailServiceByUsername implements ClientDetailsService{

    private final ClientService clientService;

    public ClientDetailServiceByUsername (ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
       return clientService.findClientByUsername(clientId)
        .map(ClientDetailWrapper::new)
        .orElseThrow(() -> new ClientRegistrationException(clientId));
    }
    
}
