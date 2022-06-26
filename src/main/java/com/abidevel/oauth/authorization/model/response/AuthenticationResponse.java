package com.abidevel.oauth.authorization.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticationResponse {
    private String responseDate;
    private boolean isAuthenticated;
    private String token;
    private String message;
}
