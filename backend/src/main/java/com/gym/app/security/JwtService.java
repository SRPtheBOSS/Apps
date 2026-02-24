package com.gym.app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    @Value("${app.jwt.secret}") private String secret;
    @Value("${app.jwt.access-expiration-ms}") private long accessTtl;
    @Value("${app.jwt.refresh-expiration-ms}") private long refreshTtl;

    public String generateAccessToken(String subject, Map<String, Object> claims) { return buildToken(subject, claims, accessTtl); }
    public String generateRefreshToken(String subject) { return buildToken(subject, Map.of(), refreshTtl); }

    private String buildToken(String subject, Map<String, Object> claims, long ttl) {
        Date now = new Date();
        return Jwts.builder().claims(claims).subject(subject).issuedAt(now).expiration(new Date(now.getTime() + ttl)).signWith(key()).compact();
    }

    public String extractUsername(String token) { return parseClaims(token).getSubject(); }
    public boolean isValid(String token) { return parseClaims(token).getExpiration().after(new Date()); }

    private Claims parseClaims(String token) {
        return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload();
    }

    private SecretKey key() { return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); }
}
