package com.abidevel.oauth.authorization.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity.IgnoredRequestConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.abidevel.oauth.authorization.configuration.filter.IssueJWTFilter;

@Configuration
public class WebSecurityFilterChain {

    private final IssueJWTFilter issueJWTFilter;

    public WebSecurityFilterChain (IssueJWTFilter issueJWTFilter) {
        this.issueJWTFilter = issueJWTFilter;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return this::extracted;
    }

    private IgnoredRequestConfigurer extracted(WebSecurity web) {
        return web.ignoring().antMatchers("/ignore1", "/ignore2");
    }

    @Bean
    WebMvcConfigurer corsConfigurer () {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // TODO Auto-generated method stub
                WebMvcConfigurer.super.addCorsMappings(registry);
            }
            
        };
    }
}
