package com.kiradprodserv.mycart.auth.controller;

import com.kiradprodserv.mycart.auth.dto.AuthResponse;
import com.kiradprodserv.mycart.auth.dto.LogInRequest;
import com.kiradprodserv.mycart.auth.dto.RegisterRequest;
import com.kiradprodserv.mycart.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        AuthResponse response = authService.register(registerRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LogInRequest logInRequest) {
        AuthResponse response = authService.login(logInRequest);
        return ResponseEntity.ok(response);
    }

}
