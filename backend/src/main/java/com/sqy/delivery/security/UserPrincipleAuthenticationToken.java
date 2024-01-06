package com.sqy.delivery.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserPrincipleAuthenticationToken extends AbstractAuthenticationToken {

    private final UserPrinciple userPrinciple;

    public UserPrincipleAuthenticationToken(UserPrinciple userPrinciple) {
        super(userPrinciple.getAuthorities());
        this.userPrinciple = userPrinciple;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserPrinciple getPrincipal() {
        return userPrinciple;
    }
}