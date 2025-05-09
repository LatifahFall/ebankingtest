package org.latifah.securelogin.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    private final String SECRET_KEY = "secret";

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Extract the username from the JWT token
    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build() // Build the parser
                .parseSignedClaims(token) // Parse the signed claims
                .getPayload(); // Get the payload (claims) from the token
        return claims.getSubject(); // Extract the subject (username)
    }

    // Extract the role from the JWT token
    public String extractRole(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build() // Build the parser
                .parseSignedClaims(token) // Parse the signed claims
                .getPayload(); // Get the payload (claims) from the token
        return (String) claims.get("role"); // Extract the role claim
    }

    // Check if the token is valid
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
