package com.kiradprodserv.mycart.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/homepage")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to our Website");
    }


}
