package com.dilsonjlrjr.javatrellodashboardmateus.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foo")
public class FooResource {

    @GetMapping
    public ResponseEntity<String> getFoo() {
        return ResponseEntity.ok("Trello dashboard Mateus");
    }
}
