package org.latifah.securelogin.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);  // This creates a secure 256-bit key

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }

    // Extract the username from the JWT token
    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build() // Build the parser
                .parseSignedClaims(token) // Parse the signed claims
                .getPayload(); // Get the payload (claims) from the token
        return claims.getSubject(); // Extract the subject (username)
    }

    // Extract the role from the JWT token
    public String extractRole(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build() // Build the parser
                .parseSignedClaims(token) // Parse the signed claims
                .getPayload(); // Get the payload (claims) from the token
        return (String) claims.get("role"); // Extract the role claim
    }

    // Check if the token is valid
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(key).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
