package com.ahl.jwt.security.jwt;

import com.ahl.jwt.security.UserDetails;

public interface JwtTokenFactory {
    String createAccessToken(UserDetails userDetails);
    String createRefreshToken(UserDetails userDetails);
}
