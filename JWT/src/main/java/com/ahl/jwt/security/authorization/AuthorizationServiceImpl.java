package com.ahl.jwt.security.authorization;

import com.ahl.jwt.security.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthorizationServiceImpl implements AuthorizationService {
    @Override
    public boolean hasAccess(Authentication authentication, String role) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList()).contains(role);
    }
}
