package com.abidevel.oauth.authorization.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Component
@PropertySource(value = "classpath:user.properties")
@ConfigurationProperties(prefix = "spring.user")
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class UserConfiguration {
    private String username;
    private String password;
}
