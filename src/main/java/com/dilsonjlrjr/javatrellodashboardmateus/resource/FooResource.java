package com.dilsonjlrjr.javatrellodashboardmateus.resource;

import com.dilsonjlrjr.javatrellodashboardmateus.model.BodyTemp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foo")
@Slf4j
public class FooResource {

    @PostMapping
    public ResponseEntity<String> getFoo(@RequestBody BodyTemp body) {
        log.info(body.toString());
        return ResponseEntity.ok("Trello dashboard Mateus");
    }
}
