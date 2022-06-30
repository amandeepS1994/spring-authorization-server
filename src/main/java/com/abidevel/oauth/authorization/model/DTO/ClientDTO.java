package com.abidevel.oauth.authorization.model.DTO;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class ClientDTO {
    private long id;  
    private String username;
    private String secret;
    private LocalDateTime createdAt;
    private Set<GrantDTO> grants = new HashSet<>();
    private Set<ScopeDTO> scopes = new HashSet<>();
}

