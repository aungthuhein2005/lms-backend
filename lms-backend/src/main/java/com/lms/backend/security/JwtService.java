package com.lms.backend.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lms.backend.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	@Value("${jwt.expiration}")
	private long jwtExpirationMs;
	
	public String generateToken(User user) {
		return Jwts.builder()
				.setSubject(user.getEmail())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();
	}
	
	public String getEmailFromToken(String token) {
		return Jwts.parser() 
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
}
