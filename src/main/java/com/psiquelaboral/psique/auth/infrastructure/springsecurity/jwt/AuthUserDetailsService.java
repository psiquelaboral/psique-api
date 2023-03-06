package com.psiquelaboral.psique.auth.infrastructure.springsecurity.jwt;

import com.psiquelaboral.psique.user.domain.dao.IPsiqueUserDao;
import com.psiquelaboral.psique.user.domain.model.PsiqueUser;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService{
	private final IPsiqueUserDao userDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PsiqueUser user = userDao.getByEmail(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		
		Collection<SimpleGrantedAuthority> authorities = this.getAuthoritiesFromUser(user);
		return new User(user.getEmail(), user.getPassword(), authorities);
	}
	
	private Collection<SimpleGrantedAuthority> getAuthoritiesFromUser(PsiqueUser user){
		return user.getRoles()
			.stream()
			.map(role -> new SimpleGrantedAuthority(role.getName().toString()))
			.collect(Collectors.toList());
	}

}
