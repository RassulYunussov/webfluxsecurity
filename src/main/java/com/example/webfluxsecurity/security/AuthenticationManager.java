package com.example.webfluxsecurity.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author rassulyunussov
 */
@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {
    private final WebClient webClient;

    public AuthenticationManager(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/login")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return webClient.post()
                .header("Authorization","Bearer bla-bla")
                .retrieve()
                .bodyToMono(String.class)
                .map(r->new AuthenticatedUser());
    }
}