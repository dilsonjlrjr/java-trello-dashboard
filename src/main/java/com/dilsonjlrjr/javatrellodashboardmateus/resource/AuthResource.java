package com.dilsonjlrjr.javatrellodashboardmateus.resource;

import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.AuthDtoRequest;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.AuthDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.User;
import com.dilsonjlrjr.javatrellodashboardmateus.service.AuthService;
import com.dilsonjlrjr.javatrellodashboardmateus.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    private final AuthService authService;

    private final JWTService jwtService;

    @Autowired
    public AuthResource(AuthService authService,
                        JWTService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<AuthDtoResponse> doLogin(@RequestBody AuthDtoRequest auth) {
        User user = authService.authenticate(auth.getUsername(), auth.getPassword());

        String token = jwtService.doCreateToken(user.getUsername());
        String refreshToken = jwtService.doCreateRefreshToken(user.getUsername());

        return ResponseEntity.ok(AuthDtoResponse.builder().token(token).refreshToken(refreshToken).build());
    }
}
