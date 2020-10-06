package com.example.webfluxsecurity.security;


import org.springframework.http.HttpHeaders;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rassulyunussov
 */
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {

    private static final String TOKEN_PREFIX = "Bearer ";

    private AuthenticationManager authenticationManager;

    List<PathPattern> pathPatternList;

    public SecurityContextRepository(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        PathPattern pathPattern = new PathPatternParser().parse("/login");
        pathPatternList = new ArrayList<>();
        pathPatternList.add(pathPattern);
    }

    @Override
    public Mono load(ServerWebExchange swe) {

        ServerHttpRequest request = swe.getRequest();

        RequestPath path = request.getPath();
        if (pathPatternList.stream().anyMatch(pathPattern -> pathPattern.matches(path.pathWithinApplication()))) {
            System.out.println(path.toString() + " path excluded");
            return Mono.empty();
        }
        System.out.println("executing logic for " + path.toString() + " path");

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String authToken = null;
        //test
        authHeader = "Bearer bla-bla";
        //~test
        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
            authToken = authHeader.replace(TOKEN_PREFIX, "");
        }else {
            System.out.println("couldn't find bearer string, will ignore the header.");
        }
        if (authToken != null) {
            Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);
            return this.authenticationManager.authenticate(auth).map((authentication) -> new SecurityContextImpl(authentication));
        } else {
            return Mono.empty();
        }
    }

    @Override
    public Mono<Void> save(ServerWebExchange serverWebExchange, SecurityContext securityContext) {

        return null;
    }
}
