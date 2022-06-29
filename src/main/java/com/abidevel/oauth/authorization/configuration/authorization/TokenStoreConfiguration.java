package com.abidevel.oauth.authorization.configuration.authorization;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Configuration
@ConfigurationProperties(prefix = "token.store")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TokenStoreConfiguration {
    private String password;
    private String privateKey;
    private String alias;
}
