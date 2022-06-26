package com.abidevel.oauth.authorization.configuration.filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

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

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

@Configuration
public class ValidateJWTToken extends OncePerRequestFilter {
    
    private final JWTConfiguration jwtConfiguration;

    public ValidateJWTToken (JWTConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                Optional<String> optionalToken = retrieveJWT("Authorization", request);
                if (optionalToken.isPresent() && isJWTValid(optionalToken.get())) {
                    continueFilterChain(request, response, filterChain);
                } else {
                    returnUnauthroizedResponse(response, "Unauthorized");
                }
    }

    private boolean isJWTValid (String token) {
        try {
            Jwts.parser()
                 .setSigningKey(jwtConfiguration.getSecret())
                 .parse(token);
            return true;
         } catch (MalformedJwtException malformedJwtException) {
             logger.error("Malformed JWT");

         } catch (SignatureException signatureException) {
             logger.error("Signature Invalid Exception JWT");

         } catch (ExpiredJwtException expiredJwtException) {
             logger.error("Expired JWT");

         } catch (IllegalArgumentException illegalArgumentException) {
             logger.error("Illegal Argument");
        }
        return false;
    }

    private void continueFilterChain (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(request, response);
    }

    private Optional<String> retrieveJWT (String header, HttpServletRequest request) {
        Optional<String> headerValue = retrieveHeaderValue(header, request);
        return headerValue.isPresent() ? extractJWTFromHeaderValue(headerValue.get()) : Optional.empty();
    }

    private Optional<String> retrieveHeaderValue (String header, HttpServletRequest request)  {
        return Optional.ofNullable(request.getHeader(header));
    }

    private Optional<String> extractJWTFromHeaderValue (String authroizationHeaderValue) {
        return Optional.ofNullable(authroizationHeaderValue.split(jwtConfiguration.getPrefix())[1]);
    }

    private void returnUnauthroizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.getOutputStream()
            .println(new ObjectMapper()
                .writer()
                .withDefaultPrettyPrinter()
                .writeValueAsString(AuthenticationResponse.builder()
                    .isAuthenticated(false)
                    .responseDate(LocalDateTime.now().toString())
                    .message(message)
                    .build()));
        response.flushBuffer();
    }    
    
}
