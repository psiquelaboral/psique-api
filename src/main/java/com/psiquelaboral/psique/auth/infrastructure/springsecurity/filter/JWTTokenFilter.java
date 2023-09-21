package com.psiquelaboral.psique.auth.infrastructure.springsecurity.filter;

import com.psiquelaboral.psique.auth.infrastructure.springsecurity.jwt.JWTService;
import com.psiquelaboral.psique.employee.domain.model.Employee;
import com.psiquelaboral.psique.user.domain.model.PsiqueUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JWTTokenFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    @SuppressWarnings("NullableProblems")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        //check the Bearer token exist on request
        if (!this.hasAuthorizationBearer(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        //Get  token from request
        String token = this.getAccessToken(request);

        //Validate token and get user
        if (token.endsWith("+employee")) {
            token = token.replace("+employee", "");
            Employee employee = this.jwtService.getEmployeeFromToken(token);
            this.setAuthenticationContext(employee, request);

        } else {
            PsiqueUser user = this.jwtService.getUserFromToken(token);
            //Set the authentication context with
            //user obtained from token.
            this.setAuthenticationContext(user, request);
        }

        //invoke filterChain
        filterChain.doFilter(request, response);
    }

    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return !ObjectUtils.isEmpty(header) && header.startsWith("Bearer");
    }

    private String getAccessToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String[] array = token.split(" ");
        token = array[1].trim();
        return token;
    }

    private void setAuthenticationContext(PsiqueUser user, HttpServletRequest request) {

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

    private void setAuthenticationContext(Employee employee, HttpServletRequest request) {
        PsiqueUser user = new PsiqueUser();
        user.setId(employee.getId());
        user.setEmail(employee.getName());

        Collection<SimpleGrantedAuthority> roles =
            Collections.singleton(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));

        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(user, null, roles);

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


}
