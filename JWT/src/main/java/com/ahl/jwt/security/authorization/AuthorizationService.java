package com.ahl.jwt.security.authorization;

import org.springframework.security.core.Authentication;

public interface AuthorizationService {
    boolean hasAccess(Authentication authentication, String role);
}
