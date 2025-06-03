package com.example.demo.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TokenEntity;
import com.example.demo.repository.TokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {
	
	@Autowired
	private TokenRepository tokenRepository;

    // Secure key - must be at least 32 characters for HS256
    private static final String SECRET = "my-super-secret-key-that-is-strong-123!";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    


    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private static long expiryDuration = 24L * 60L * 60L; // 24 hours 

    public String generateToken(UserDetails userDetails, Map<String, Object> extraClaims) {
         String token = Jwts
        	.builder()
            .setClaims(extraClaims) // Custom claims add
            .setSubject(userDetails.getUsername()) // Username (default claim)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
            .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
            .compact();
         saveToken(userDetails.getUsername(), token);
         
		return token;
    }
    
    public String saveToken(String userName,String token) {
    	
    	TokenEntity tokenEntity = new TokenEntity();
    	
    	tokenEntity.setUserName(userName);
    	tokenEntity.setToken(token);
    	tokenEntity.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 * 5));
    	tokenRepository.save(tokenEntity);
    	
    	
    	return "Token Save SuccessFully";
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
  
         return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }
}
