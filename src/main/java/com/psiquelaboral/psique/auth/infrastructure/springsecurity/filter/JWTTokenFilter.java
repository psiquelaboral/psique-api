package com.psiquelaboral.psique.auth.infrastructure.springsecurity.filter;

import com.psiquelaboral.psique.auth.infrastructure.springsecurity.jwt.JWTService;
import com.psiquelaboral.psique.user.domain.model.PsiqueUser;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTTokenFilter extends OncePerRequestFilter{

	private final JWTService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//check the Bearer token exist on request
		if(!this.hasAuthorizationBearer(request)) {
			filterChain.doFilter(request, response);
			return;
		}	
		
		//Get  token from request
		String token = this.getAccessToken(request);
		
		//Validate token and get user
		PsiqueUser user = this.jwtService.getUserFromToken(token);
			
		//Set the authentication context with
		//user obtained from token.
		 this.setAuthenticationContext(user, request);
		 
		//invoke filterChain
		filterChain.doFilter(request, response);
	}
	
	private boolean hasAuthorizationBearer(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		return !ObjectUtils.isEmpty(header) && header.startsWith("Bearer");
	}

	private String getAccessToken(HttpServletRequest request) {
		return request.getHeader("Authorization").split(" ")[1].trim();
	}
	
	private void setAuthenticationContext(PsiqueUser user, HttpServletRequest request ) {
		
		List<SimpleGrantedAuthority> authorities = 
				user.getRoles()
					.stream()
					.map(role -> new SimpleGrantedAuthority(role.getName().toString()))
					.collect(Collectors.toList());
		
		UsernamePasswordAuthenticationToken authentication 
					= new UsernamePasswordAuthenticationToken(user, null, authorities);
		
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	

}
