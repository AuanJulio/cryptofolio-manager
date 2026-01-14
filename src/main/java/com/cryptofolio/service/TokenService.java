package com.cryptofolio.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cryptofolio.dto.response.UserResponse;
import com.cryptofolio.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TokenService {

    @Value("${cryptofolio.jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("CryptoFolio")
                    .withSubject(user.getEmail())
                    .withClaim("userId", user.getId())
                    .withClaim("name", user.getUsername())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(86400))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Could not create JWT", exception);
        }
    }

    public Optional<UserResponse> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("CryptoFolio")
                    .build()
                    .verify(token);

            return Optional.of(UserResponse.builder()
                    .id(decodedJWT.getClaim("userId").asString())
                    .username(decodedJWT.getClaim("name").asString())
                    .email(decodedJWT.getSubject())
                    .build());

        } catch (JWTVerificationException exception) {
            return Optional.empty();
        }
    }

}
