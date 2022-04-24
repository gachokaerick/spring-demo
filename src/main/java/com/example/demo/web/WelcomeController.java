package com.example.demo.web;

import com.example.demo.config.Application;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeController {
    private final Application application;

    public WelcomeController(Application application) {
        this.application = application;
    }

    @GetMapping
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok(application.getWelcome());
    }
}
