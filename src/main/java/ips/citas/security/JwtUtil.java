package ips.citas.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private final String jwtSecret = "UnaClaveSecretaMuySeguraParaElJWT123456789"; // Debe tener mínimo 32 caracteres
    private final long jwtExpirationMs = 86400000; // 1 día

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(String username, String rol) {
        return Jwts.builder()
                .setSubject(username)
                .claim("authorities", List.of(rol))  // Cambiar a 'authorities' que Spring entiende por defecto
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public String extractRol(String token) {
        return (String) Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token)
                .getBody().get("rol");
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    public List<String> extractAuthorities(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("authorities", List.class);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
