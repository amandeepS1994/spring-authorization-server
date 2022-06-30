package com.abidevel.oauth.authorization.model.DTO;

import com.abidevel.oauth.authorization.model.enumeration.GrantType;

import lombok.Data;

@Data
public class GrantDTO {
    private Long id;
    private GrantType name;
}

