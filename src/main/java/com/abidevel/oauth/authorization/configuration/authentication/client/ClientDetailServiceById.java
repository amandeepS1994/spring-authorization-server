package com.abidevel.oauth.authorization.configuration.authentication.client;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.abidevel.oauth.authorization.service.ClientService;

@Service(value = "clientDetailById")
public class ClientDetailServiceById implements ClientDetailsService{

    private final ClientService clientService;

    public ClientDetailServiceById (ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
       return clientService.findByClientById(clientId)
        .map(ClientDetailWrapper::new)
        .orElseThrow(() -> new ClientRegistrationException(clientId));
    }
    
}
