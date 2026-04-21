package com.app.greenvibe.controller;

import com.app.greenvibe.dto.request.CustomerRegisterRequestDto;
import com.app.greenvibe.dto.request.LoginRequestDto;
import com.app.greenvibe.dto.response.AuthenticationResponse;
import com.app.greenvibe.dto.response.CustomerResponseDto;
import com.app.greenvibe.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerResponseDto> register(@RequestBody CustomerRegisterRequestDto request) {
        return ResponseEntity.status(201).body(customerService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(customerService.login(request));
    }
}
