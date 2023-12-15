package com.ascend.apigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ascend.apigateway.model.AuthRequest;
import com.ascend.apigateway.model.AuthResponse;
import com.ascend.apigateway.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9100")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/token")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.register(authRequest));
    }
}
