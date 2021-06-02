package com.dilsonjlrjr.javatrellodashboardmateus.service.auth;

import com.dilsonjlrjr.javatrellodashboardmateus.exception.ServiceException;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.code.EnumAuthServiceCode;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.message.EnumAuthServiceMessage;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request.AuthRefreshDtoRequest;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.AuthLoginDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.User;
import com.dilsonjlrjr.javatrellodashboardmateus.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class AuthService implements UserDetailsService {

    private final UserService userService;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    private final Integer tokenTimeToExpire;
    private final Integer refreshTokenTimeToExpire;

    private static final String TOKEN_TYPE_REFRESH = "refresh";

    @Autowired
    public AuthService(UserService userService,
                       JWTService jwtService,
                       @Lazy AuthenticationManager authenticationManager,
                       @Value("${spring.application.jwt.timeToExpire.token}") Integer tokenTimeToExpire,
                       @Value("${spring.application.jwt.timeToExpire.refreshToken}") Integer refreshTokenTimeToExpire) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;

        this.tokenTimeToExpire = tokenTimeToExpire;
        this.refreshTokenTimeToExpire = refreshTokenTimeToExpire;
    }

    public AuthLoginDtoResponse authenticate(String username, String password) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        User user =  userService.getByUsername(username);
        String hashSession = doCreateHashSession(username);

        AuthLoginDtoResponse authLoginDtoResponse = doCreateAuthTokenResponse(user.getUsername(), hashSession, tokenTimeToExpire, refreshTokenTimeToExpire);
        updateSessionUser(user.getId(), hashSession, authLoginDtoResponse.getRefreshToken());
        return authLoginDtoResponse;
    }

    @SneakyThrows
    private String doCreateHashSession(String username) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        MessageDigest md5encoder = MessageDigest.getInstance("MD5");
        String message = String.valueOf(nowDateTime.atZone(ZoneId.systemDefault()).toEpochSecond()).concat(username);
        md5encoder.update(message.getBytes(StandardCharsets.UTF_8), 0, message.length());

        return new BigInteger(1, md5encoder.digest()).toString(16);
    }

    private AuthLoginDtoResponse doCreateAuthTokenResponse(String sub,
                                                           String hashSession,
                                                           Integer timeExpirationToken,
                                                           Integer timeExpirationRefreshToken) {
        String token = jwtService.doCreateToken(sub, hashSession, timeExpirationToken);
        String refreshToken = jwtService.doCreateRefreshToken(sub, hashSession, timeExpirationRefreshToken);

        return AuthLoginDtoResponse.builder()
                .tokenTimeExpiration(timeExpirationToken)
                .token(token)
                .refreshTokenTimeExpiration(timeExpirationRefreshToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getByUsername(username);
    }

    public void updateSessionUser(Long id, String hashSession, String refreshToken) {
        userService.updateSessionUser(id, hashSession, refreshToken);
    }

    public AuthLoginDtoResponse refreshToken(AuthRefreshDtoRequest auth) {
        String type = (String) jwtService.getClaimFromToken(auth.getRefreshToken(), "typ");
        String hashSession = (String) jwtService.getClaimFromToken(auth.getRefreshToken(), "jti");

        if (!type.equals(TOKEN_TYPE_REFRESH))
            throw new ServiceException(EnumAuthServiceMessage.TOKEN_TYPE_INVALID.getMessage(),
                    EnumAuthServiceCode.TOKEN_TYPE_INVALID.getCode());

        User user = userService.getByUsername(auth.getRefreshToken(), hashSession);

        hashSession = doCreateHashSession(user.getUsername());

        AuthLoginDtoResponse authLoginDtoResponse = doCreateAuthTokenResponse(user.getUsername(),
                hashSession, tokenTimeToExpire, refreshTokenTimeToExpire);
        updateSessionUser(user.getId(), hashSession, authLoginDtoResponse.getRefreshToken());

        return authLoginDtoResponse;
    }
}
