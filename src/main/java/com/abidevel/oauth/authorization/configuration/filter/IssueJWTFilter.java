package com.abidevel.oauth.authorization.configuration.filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import com.abidevel.oauth.authorization.configuration.JWTConfiguration;
import com.abidevel.oauth.authorization.model.response.AuthenticationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class IssueJWTFilter extends OncePerRequestFilter {

    private final JWTConfiguration jwtConfiguration;

    public IssueJWTFilter (JWTConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
            log.info("Generating Token.");
            Map<String, Object> claims = new HashMap<>();
            claims.put("issuer", "oauth-Service");
            generateResponse(response,  generateJWTToken(claims, "Auth Token"));
    }   

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getRequestURI().equals("/api/oauth/token/");
    }

    private Optional<String> generateJWTToken (Map<String, Object> claims, String subject) {
        LocalDateTime tokenIssueDateTime = LocalDateTime.now();
         return Optional.ofNullable(Jwts.builder()
            .setIssuedAt(Date.from(tokenIssueDateTime.atZone(ZoneId.systemDefault()).toInstant()))
            .setExpiration(Date.from(tokenIssueDateTime.plusHours(4).atZone(ZoneId.systemDefault()).toInstant()))
            .addClaims(claims)
            .setId(UUID.randomUUID().toString())
            .setSubject(subject)
            .signWith(SignatureAlgorithm.HS256, jwtConfiguration.getSecret())
            .compact());
    }


    private void generateResponse (HttpServletResponse response, Optional<String> token) throws IOException {
        response.setHeader(String.format("Authorization %s", jwtConfiguration.getPrefix()), token.get());
        response.setContentType("application/json");
        response.setStatus((token.isPresent()) ? HttpServletResponse.SC_OK : HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter()
                .println(new ObjectMapper()
                    .writer()
                    .withDefaultPrettyPrinter()
                    .writeValueAsString(buildAuthorizationResponse(token))
                );
        response.flushBuffer();
    }
    
    private AuthenticationResponse buildAuthorizationResponse (Optional <String> token) {
        return token.isPresent() ? AuthenticationResponse.builder()
            .isAuthenticated(true)
            .responseDate(LocalDateTime.now().toString())
            .token(token.get())
            .message("Token Generated.")
            .build() : AuthenticationResponse.builder()
            .isAuthenticated(false)
            .responseDate(LocalDateTime.now().toString())
            .message("Failed to Provide Access Token.")
            .build();
    }
    
}
