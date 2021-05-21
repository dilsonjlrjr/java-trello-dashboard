package com.dilsonjlrjr.javatrellodashboardmateus.resource;

import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request.AuthDtoRequest;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.AuthDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    private final AuthService authService;

    @Autowired
    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<AuthDtoResponse> doLogin(@RequestBody @Valid AuthDtoRequest auth) {
        AuthDtoResponse token = authService.authenticate(auth.getUsername(), auth.getPassword());
        return ResponseEntity.ok(token);
    }
}
