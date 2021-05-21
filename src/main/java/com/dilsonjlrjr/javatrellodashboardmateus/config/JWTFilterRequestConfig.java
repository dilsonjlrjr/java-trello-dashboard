package com.dilsonjlrjr.javatrellodashboardmateus.config;

import com.dilsonjlrjr.javatrellodashboardmateus.exception.code.EnumMainControllerAdviceCode;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.message.EnumMainControllerAdviceMessage;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.HttpErrorDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.User;
import com.dilsonjlrjr.javatrellodashboardmateus.service.auth.JWTService;
import com.dilsonjlrjr.javatrellodashboardmateus.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    private final UserService userService;
    private final JWTService jwtService;

    private static final String HEADER_BEARER = "Bearer ";
    private static final String CLAIM_TYPE = "typ";
    private static final String TYPE_TOKEN = "token";

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

        try {
            if (headerAuthorization != null && headerAuthorization.startsWith(HEADER_BEARER)) {
                String token = headerAuthorization.substring(7);
                String username = jwtService.getClaimFromToken(token, Claims::getSubject);
                String hashSession = jwtService.getClaimFromToken(token, Claims::getId);
                String type = (String) jwtService.getClaimFromToken(token, CLAIM_TYPE);

                if (!type.equals(TYPE_TOKEN)) {
                    writeExceptionBody(response, EnumMainControllerAdviceCode.INVALID_TOKEN.getCode(),
                            EnumMainControllerAdviceMessage.INVALID_TOKEN.getMessage());
                    return;
                }

                User user = userService.getByUsername(username);

                if (!hashSession.equals(user.getHashSession())) {
                    writeExceptionBody(response, EnumMainControllerAdviceCode.SESSION_TOKEN_INVALID.getCode(),
                            EnumMainControllerAdviceMessage.SESSION_TOKEN_INVALID.getMessage());
                    return;
                }

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException ex) {
            writeExceptionBody(response, EnumMainControllerAdviceCode.EXPIRED_TOKEN.getCode(),
                    EnumMainControllerAdviceMessage.EXPIRED_TOKEN.getMessage());
        }
    }

    @SneakyThrows
    private void writeExceptionBody(HttpServletResponse response, String code,String message) {
        HttpErrorDtoResponse error = HttpErrorDtoResponse.builder()
                .httpStatus(HttpStatus.UNAUTHORIZED.value())
                .code(code)
                .message(message).build();
        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter()
                .write(objectMapper.writeValueAsString(error));
    }
}
