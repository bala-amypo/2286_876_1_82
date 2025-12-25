// package com.example.demo.security;

// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import javax.crypto.SecretKey;
// import java.util.Date;
// import java.util.Set;

// @Component
// public class JwtTokenProvider {
    
//     private final SecretKey key;
//     private final long expirationMillis;

//     public JwtTokenProvider(@Value("${jwt.secret:mySecretKey}") String secretKey, 
//                            @Value("${jwt.expiration:86400000}") long expirationMillis) {
//         this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
//         this.expirationMillis = expirationMillis;
//     }

//     public String generateToken(Long userId, String email, Set<String> roles) {
//         Date now = new Date();
//         Date expiryDate = new Date(now.getTime() + expirationMillis);

//         return Jwts.builder()
//                 .setSubject(email)
//                 .claim("userId", userId)
//                 .claim("roles", roles)
//                 .setIssuedAt(now)
//                 .setExpiration(expiryDate)
//                 .signWith(key)
//                 .compact();
//     }

//     public Claims validateToken(String token) {
//         try {
//             return Jwts.parserBuilder()
//                     .setSigningKey(key)
//                     .build()
//                     .parseClaimsJws(token)
//                     .getBody();
//         } catch (JwtException | IllegalArgumentException e) {
//             throw new RuntimeException("Invalid JWT token", e);
//         }
//     }

//     public String getEmailFromToken(String token) {
//         Claims claims = validateToken(token);
//         return claims.getSubject();
//     }

//     public Long getUserIdFromToken(String token) {
//         Claims claims = validateToken(token);
//         return claims.get("userId", Long.class);
//     }
// }
package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenProvider {
    private final String secretKey = "your-secret-key"; // Should be from config
    private final long expiration = 3600000; // 1 hour

    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
}