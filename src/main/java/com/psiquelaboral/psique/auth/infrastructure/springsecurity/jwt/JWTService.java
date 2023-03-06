package com.psiquelaboral.psique.auth.infrastructure.springsecurity.jwt;

import com.psiquelaboral.psique.user.domain.model.PsiqueUser;
import com.psiquelaboral.psique.user.domain.model.Role;
import com.psiquelaboral.psique.user.domain.model.Role.RoleName;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	private final String JWT_SECRET_KEY = "txKbfHrSjzM7VOcTvyiKYLKhdej32KGB1hdlRx79";
	
	public String generateToken(PsiqueUser user) {
		
		Instant now = Instant.now();
		
		String roles = user.getRoles().stream()
			.map(role -> role.getName().toString())
			.collect(Collectors.joining(","));
					
		return Jwts
			.builder()
				.setSubject(user.getEmail())
				.setExpiration(Date.from(now.plus(24, ChronoUnit.HOURS)))
				.claim("id", user.getId())
				.claim("roles", roles)
				.signWith(Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes()))
			.compact();
	}
	
	public PsiqueUser getUserFromToken(String token) {
		//get claims and validate token
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(JWT_SECRET_KEY.getBytes())
				.build()
				.parseClaimsJws(token)
				.getBody();
		
		//Get subject and id
		String email = claims.getSubject();
		String id = claims.get("id", String.class);
		
		//Get roles from claims
		String strRoles = claims.get("roles", String.class); 
		String[] arrayRoles = strRoles.split(",");
		
		List<Role> roles = Arrays.stream(arrayRoles)
			.map(role -> new Role(RoleName.valueOf(role)))
			.collect(Collectors.toList());
		
		//Crate user 	
		return PsiqueUser
				.builder()
					.id(id)
					.email(email)
					.roles(roles)
				.build();
	}
	
}
