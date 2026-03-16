package com.capgi.STUDENT_SERVICE.controller;

import com.capgi.STUDENT_SERVICE.dto.request.LoginRequestDTO;
import com.capgi.STUDENT_SERVICE.dto.request.RegisterRequestDTO;
import com.capgi.STUDENT_SERVICE.dto.response.AuthResponseDTO;
import com.capgi.STUDENT_SERVICE.dto.response.RegisterResponseDTO;
import com.capgi.STUDENT_SERVICE.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(authService.login(request));
    }
}