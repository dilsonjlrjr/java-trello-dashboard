package com.dilsonjlrjr.javatrellodashboardmateus.service.auth;

import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.AuthDtoResponse;
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
import sun.security.provider.MD5;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class AuthService implements UserDetailsService {

    private final UserService userService;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    private final Integer tokenTimeToExpire;
    private final Integer refreshTokenTimeToExpire;

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

    public AuthDtoResponse authenticate(String username, String password) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        User user =  userService.getByUsername(username);
        String hashSession = doCreateHashSession(username);

        return doCreateAuthTokenResponse(user.getUsername(), hashSession, tokenTimeToExpire, refreshTokenTimeToExpire);
    }

    @SneakyThrows
    private String doCreateHashSession(String username) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        MessageDigest md5encoder = MessageDigest.getInstance("MD5");
        String message = String.valueOf(nowDateTime.atZone(ZoneId.systemDefault()).toEpochSecond()).concat(username);
        md5encoder.update(message.getBytes(StandardCharsets.UTF_8), 0, message.length());

//        return new BigInteger(1, md5encoder.digest()).toString(16);
        return "e10adc3949ba59abbe56e057f20f883e";
    }

    private AuthDtoResponse doCreateAuthTokenResponse(String sub,
                                                      String hashSession,
                                                      Integer timeExpirationToken,
                                                      Integer timeExpirationRefreshToken) {
        String token = jwtService.doCreateToken(sub, hashSession, timeExpirationToken);
        String refreshToken = jwtService.doCreateRefreshToken(sub, hashSession, timeExpirationRefreshToken);

        return AuthDtoResponse.builder()
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
}
