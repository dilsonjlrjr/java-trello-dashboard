package com.dilsonjlrjr.javatrellodashboardmateus.config;

import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.User;
import com.dilsonjlrjr.javatrellodashboardmateus.service.JWTService;
import com.dilsonjlrjr.javatrellodashboardmateus.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class JWTFilterRequestConfig extends OncePerRequestFilter {

    private static String HEADER_BEARER = "Bearer ";

    private final UserService userService;
    private final JWTService jwtService;

    @Autowired
    public JWTFilterRequestConfig(UserService userService,
                                  JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String headerAuthorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (headerAuthorization != null && headerAuthorization.startsWith(HEADER_BEARER)) {
            String token = headerAuthorization.substring(7);
            String username = jwtService.getClaimFromToken(token, Claims::getSubject);

            User user = userService.getByUsername(username);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
