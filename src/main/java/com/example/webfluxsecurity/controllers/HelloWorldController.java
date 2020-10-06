package com.example.webfluxsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

/**
 * @author rassulyunussov
 */
@RestController
public class HelloWorldController {
    @GetMapping("hello")
    Mono<Principal> hello(Mono<Principal> principal) {
        return principal;
       // return Mono.just("Hello world");
    }
    @PostMapping("login")
    Mono<String> login() {
        return Mono.just("Bearer asdf");
    }
}
