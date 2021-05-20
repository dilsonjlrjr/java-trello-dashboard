package com.dilsonjlrjr.javatrellodashboardmateus.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.Function;

@Service
public class JWTService {

    private static final String CLAIM_EXP = "exp";
    private static final String CLAIM_TYP = "typ";

    private final String secret;

    private final Integer tokenTimeToExpire;
    private final Integer refreshTokenTimeToExpire;

    public JWTService(@Value("${spring.application.jwt.secret}") String secret,
                      @Value("${spring.application.jwt.timeToExpire.token}") Integer tokenTimeToExpire,
                      @Value("${spring.application.jwt.timeToExpire.refreshToken}") Integer refreshTokenTimeToExpire) {
        this.secret = secret;
        this.tokenTimeToExpire = tokenTimeToExpire;
        this.refreshTokenTimeToExpire = refreshTokenTimeToExpire;
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private String getExp(Integer timeExpiration) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        return String.valueOf(nowDateTime.plusMinutes(timeExpiration).atZone(ZoneId.systemDefault()).toEpochSecond());
    }

    //para retornar qualquer informação do token nos iremos precisar da secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(doGenerateSignedKey()).build().parseClaimsJws(token).getBody();
    }

    private SecretKey doGenerateSignedKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String doCreateToken(String subject) {
        return Jwts.builder()
                .signWith(doGenerateSignedKey())
                .claim(CLAIM_EXP, getExp(tokenTimeToExpire))
                .claim(CLAIM_TYP, "token")
                .setSubject(subject)
                .compact();
    }

    public String doCreateRefreshToken(String subject) {
        return Jwts.builder()
                .signWith(doGenerateSignedKey())
                .claim(CLAIM_EXP, getExp(refreshTokenTimeToExpire))
                .claim(CLAIM_TYP, "refresh")
                .setSubject(subject)
                .compact();
    }
}
