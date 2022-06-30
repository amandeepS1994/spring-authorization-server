package com.abidevel.oauth.authorization.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.abidevel.oauth.authorization.model.entity.Client;
import com.abidevel.oauth.authorization.model.entity.Grant;
import com.abidevel.oauth.authorization.model.entity.Scope;
import com.abidevel.oauth.authorization.model.enumeration.GrantType;
import com.abidevel.oauth.authorization.model.enumeration.ScopeType;
import com.abidevel.oauth.authorization.model.request.CreateClientRequest;
import com.abidevel.oauth.authorization.repository.ClientRepository;
import com.abidevel.oauth.authorization.repository.GrantRepository;
import com.abidevel.oauth.authorization.repository.ScopeRepository;
import com.abidevel.oauth.authorization.service.ClientService;
import com.abidevel.oauth.authorization.utility.ClientUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientServiceImplementation  implements ClientService{
    private final ClientRepository clientRepository;
    private final GrantRepository grantRepository;
    private final ScopeRepository scopeRepository;
    

    public ClientServiceImplementation(ClientRepository clientRepository, GrantRepository grantRepository, ScopeRepository scopeRepository) {
        this.clientRepository = clientRepository;
        this.grantRepository = grantRepository;
        this.scopeRepository = scopeRepository;
    }

    @Override
    public List<Client> retrieveAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> createClient(CreateClientRequest createClient) {
        Optional<Client> optionalClient = prepareClient(createClient);
        return optionalClient.isPresent() ? persistClient(optionalClient.get()) : Optional.empty();
    }


    private Optional<Client> prepareClient(CreateClientRequest clientRequest) {
        if (validateParameters(clientRequest)) {
            // now check if grant and scope type is available
            Optional<Grant> clientGrant = grantRepository.findByName(GrantType.valueOf(clientRequest.getGrantType()));
            Optional<Scope> clientScope = scopeRepository.findByName(ScopeType.valueOf(clientRequest.getScopeType()));
            if (clientGrant.isPresent() && clientScope.isPresent()) {
                return Optional.ofNullable(
                    Client.builder()
                        .username(clientRequest.getUsername())
                        .secret(clientRequest.getSecret())
                        .build()
                        .addGrant(clientGrant.get())
                        .addScope(clientScope.get())
                );
            }
            
        }
        return Optional.empty();
        
    }

    private Optional<Client> persistClient(Client client) {
        client.setCreatedAt(LocalDateTime.now());
        return Optional.ofNullable(clientRepository.save(client));
    }

    private boolean validateParameters (CreateClientRequest clientRequest) {
        return Objects.nonNull(clientRequest) && checkIfClientUsernameExists(clientRequest.getUsername()) && checkIfValuesArePresent(clientRequest) && ClientUtility.checkIfGrantTypeExist(clientRequest.getGrantType()) && ClientUtility.checkIfScopeTypeExist(clientRequest.getScopeType());
    }

    private boolean checkIfClientUsernameExists (String clientUsername) {
        return !clientRepository.existsByUsername(clientUsername);
    }

    private boolean checkIfValuesArePresent (CreateClientRequest clientRequest) {
        return !clientRequest.getUsername().isEmpty() && !clientRequest.getSecret().isEmpty() && Objects.nonNull(clientRequest.getGrantType()) && Objects.nonNull(clientRequest.getScopeType());
    }
     
}
