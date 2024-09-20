package com.example.TUDOHair.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "auth")
@Getter
@Setter
public class AuthProperty {
    private String tokenSecret;
    private long accessTokenExpirationMils;
    private long refreshTokenExpirationMils;
}
