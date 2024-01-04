package com.sqy.delivery.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtIssuer {

    private final JwtProperties jwtProperties;

    public String issue(long entityId, String login, List<String> roles) {
        return JWT.create()
                .withSubject(String.valueOf(entityId))
                .withExpiresAt(Instant.now().plus(Duration.ofHours(1)))
                .withClaim("login", login)
                .withClaim("roles", roles)
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }
}
