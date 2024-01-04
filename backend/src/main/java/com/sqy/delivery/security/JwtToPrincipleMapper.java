package com.sqy.delivery.security;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class JwtToPrincipleMapper {
    public UserPrinciple convert(DecodedJWT jwt) {
        return new UserPrinciple(
                Long.parseLong(jwt.getSubject()),
                jwt.getClaim("login").toString(),
                extractAuthoritiesFromClaim(jwt),
                null
        );
    }

    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt) {
        Claim roles = jwt.getClaim("roles");
        if (roles.isNull() || roles.isMissing()) {
            return Collections.emptyList();
        }
        return roles.asList(SimpleGrantedAuthority.class);
    }
}