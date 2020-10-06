package com.example.webfluxsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

/**
 * @author rassulyunussov
 * @created 06/10/2020 - 11:03 AM
 */
@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfiguration {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SecurityContextRepository securityContextRepository;


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity http) {
        return http
                .csrf().disable()
                .cors().disable()
                .httpBasic().disable()
                .logout().disable()
                .formLogin().disable()
                .authorizeExchange()
                .pathMatchers("/login").permitAll()
                .anyExchange().authenticated()
                .and()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .build();
    }
}
