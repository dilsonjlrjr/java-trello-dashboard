package com.dilsonjlrjr.javatrellodashboardmateus.service.auth;

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

    private static final String TYPE_TOKEN_TOKEN = "token";
    private static final String TYPE_TOKEN_REFRESH = "refresh";

    private final String secret;


    public JWTService(@Value("${spring.application.jwt.secret}") String secret) {
        this.secret = secret;
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Object getClaimFromToken(String token, String name) {
        final Claims claims = getAllClaimsFromToken(token);
        if (claims.containsKey(name))
            return claims.get(name);

        return "";
    }

    //para retornar qualquer informação do token nos iremos precisar da secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(doGenerateSignedKey()).build().parseClaimsJws(token).getBody();
    }

    private SecretKey doGenerateSignedKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    private String doConvertNowDateTimeToEpochSecond(Integer plusTime) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        return String.valueOf(nowDateTime.plusMinutes(plusTime).atZone(ZoneId.systemDefault()).toEpochSecond());
    }

    public String doCreateToken(String subject, String hashSession, Integer tokenTimeToExpire) {
        return Jwts.builder()
                .signWith(doGenerateSignedKey())
                .setId(hashSession)
                .claim(CLAIM_EXP, doConvertNowDateTimeToEpochSecond(tokenTimeToExpire))
                .claim(CLAIM_TYP, TYPE_TOKEN_TOKEN)
                .setSubject(subject)
                .compact();
    }

    public String doCreateRefreshToken(String subject, String hashSession, Integer refreshTokenTimeToExpire) {
        return Jwts.builder()
                .signWith(doGenerateSignedKey())
                .setId(hashSession)
                .claim(CLAIM_EXP, doConvertNowDateTimeToEpochSecond(refreshTokenTimeToExpire))
                .claim(CLAIM_TYP, TYPE_TOKEN_REFRESH)
                .setSubject(subject)
                .compact();
    }
}
