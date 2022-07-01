package com.abidevel.oauth.authorization.configuration.authentication.client;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.abidevel.oauth.authorization.model.entity.Client;
import com.abidevel.oauth.authorization.model.entity.Grant;
import com.abidevel.oauth.authorization.model.entity.Scope;
import com.abidevel.oauth.authorization.model.enumeration.GrantType;
import com.abidevel.oauth.authorization.model.enumeration.ScopeType;

public class ClientDetailWrapper implements ClientDetails{

    private final transient Client client;
    
    public ClientDetailWrapper(Client client) {
        this.client = client;
    }

    @Override
    public String getClientId() {
        return client.getUsername();
    }

    @Override
    public Set<String> getResourceIds() {
        return Collections.emptySet();
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return client.getSecret();
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return client.getScopes().stream()
        .map(Scope::getName)
        .map(ScopeType:: name)
        .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return client.getGrants().stream()
                .map(Grant::getName)
                .map(GrantType:: name)
                .map(String:: toLowerCase)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return Collections.emptySet();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.emptySet();
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return 300;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return 30;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return Collections.emptyMap();
    }
    
}
