package com.example.webfluxsecurity.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


import java.util.Collection;

/**
 * @author rassulyunussov
 */
public class AuthenticatedUser implements Authentication {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
