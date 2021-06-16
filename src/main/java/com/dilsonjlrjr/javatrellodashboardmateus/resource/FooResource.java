package com.dilsonjlrjr.javatrellodashboardmateus.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/foo")
public class FooResource {

    @GetMapping
    public ResponseEntity<Map<String, String>> getFoo(@RequestHeader Map<String, String> headers, HttpServletRequest request) {
        headers.put("RemoteAddr", request.getRemoteAddr());
        headers.put("RemoteHost", request.getRemoteHost());

        return ResponseEntity.ok(headers);
    }
}
