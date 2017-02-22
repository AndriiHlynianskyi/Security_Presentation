package com.ahl.jwt.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class UserDetails {
    private final String username;
    private final List<GrantedAuthority> authorities;

    public UserDetails(String username, List<GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
