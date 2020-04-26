package com.bee.master.adapter.restful.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Setter
@Configuration
@ConfigurationProperties("gaia.security.jwt")
public class JWTConfiguration {

    private String secret;

    private String issuer;

    private String audience;

    @Bean
    public JWTVerifier getJwtVerifier() {
        Algorithm alg = Algorithm.HMAC256(secret);
        return JWT.require(alg)
                .withIssuer(issuer)
                .withAudience(audience)
                .build();
    }
}
