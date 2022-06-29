package com.abidevel.oauth.authorization.model.DTO;

import com.abidevel.oauth.authorization.model.enumeration.AuthorityTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDTO {
    private long id;
    private AuthorityTypes authorityName;
}
