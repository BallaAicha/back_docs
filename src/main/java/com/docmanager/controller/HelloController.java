package com.docmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/hello")
@Tag(name = "Hello", description = "Simple hello endpoint")
public class HelloController {

    @GetMapping
    @Operation(summary = "Get a hello message")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, World!");
    }
}