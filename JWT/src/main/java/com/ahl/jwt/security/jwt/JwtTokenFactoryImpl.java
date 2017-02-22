package com.ahl.jwt.security.jwt;

import com.ahl.jwt.security.UserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtTokenFactoryImpl implements JwtTokenFactory {

    @Override
    public String createAccessToken(UserDetails userDetails) {
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("scopes", userDetails.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList()));
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.systemDefault());
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer("localhost")
                .setIssuedAt(Date.from(currentTime.toInstant()))
                .setExpiration(Date.from(currentTime.plusMinutes(15).toInstant()))
                .signWith(SignatureAlgorithm.HS512, "signKey")
                .compact();
        return token;
    }

    @Override
    public String createRefreshToken(UserDetails userDetails) {
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("scopes", "REFRESH_TOKEN");
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.systemDefault());
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer("localhost")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(currentTime.toInstant()))
                .setExpiration(Date.from(currentTime.plusMinutes(60).toInstant()))
                .signWith(SignatureAlgorithm.HS512, "signKey")
                .compact();
        return token;
    }
}
