package com.bee.master.adapter.restful.security;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import static java.util.Collections.emptySet;

@Component
@AllArgsConstructor
public class TokenHelper {

    private final JWTVerifier jwtVerifier;

    public boolean validateToken(String token) {
        try {
            jwtVerifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        String userId = getVerifiedUserId(token);
        return new UsernamePasswordAuthenticationToken(userId, null, emptySet());
    }

    private String getVerifiedUserId(String token) throws JWTVerificationException {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getSubject();
    }
}
