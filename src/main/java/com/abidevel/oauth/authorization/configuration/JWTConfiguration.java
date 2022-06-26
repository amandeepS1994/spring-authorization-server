package com.abidevel.oauth.authorization.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@PropertySource(value = "classpath:jwt.properties")
@ConfigurationProperties(prefix = "jwt")
@Data
public class JWTConfiguration {
    private String secret;
    private String prefix;
}
