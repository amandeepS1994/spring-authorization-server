package com.abidevel.oauth.authorization.model.DTO;

import com.abidevel.oauth.authorization.model.enumeration.ScopeType;

import lombok.Data;

@Data
public class ScopeDTO {
        private Long id;
        private ScopeType name;
}

