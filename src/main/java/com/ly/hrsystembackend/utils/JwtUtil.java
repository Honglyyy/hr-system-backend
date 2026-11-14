package com.ly.hrsystembackend.utils;

import com.ly.hrsystembackend.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

    private final long EXPIRATION = 1000 * 60 * 60 * 24;

    private SecretKey getSigningKey() {
        byte[] encodedKey = Base64.getDecoder().decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(encodedKey);
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmployeeId())
                .setIssuer("ly's-hr-system")
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmployeeIdFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails, String employeeId) {
        try {
            Claims claims = getClaims(token);
            String empId = claims.getSubject();
            boolean isNotExpired = claims.getExpiration() != null && claims.getExpiration().after(new Date());
            boolean validIssuer = "ly's-hr-system".equals(claims.getIssuer());

            return empId != null
                    && empId.equals(employeeId)
                    && userDetails != null
                    && empId.equals(userDetails.getUsername())
                    && validIssuer
                    && isNotExpired;
        } catch (Exception e) {
            return false;
        }
    }
}
