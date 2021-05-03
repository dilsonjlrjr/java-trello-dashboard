package com.dilsonjlrjr.javatrellodashboardmateus.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foo")
public class FooResource {

    @GetMapping
    public ResponseEntity<String> getFoo() {
        return ResponseEntity.ok("Trello dashboard Mateus");
    }
}
