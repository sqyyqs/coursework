package com.sqy.delivery.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {

    @Value("secret-key")
    private String secretKey;

}
