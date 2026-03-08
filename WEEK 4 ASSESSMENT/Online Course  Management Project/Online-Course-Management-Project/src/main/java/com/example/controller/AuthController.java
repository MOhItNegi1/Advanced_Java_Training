package com.example.controller;

import com.example.dto.LoginRequestDTO;
import com.example.dto.RegisterRequestDTO;
import com.example.dto.UserResponseDTO;
import com.example.entity.User;
import com.example.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // REGISTER API
    @PostMapping("/register")
    public UserResponseDTO register(@Valid @RequestBody RegisterRequestDTO dto) {

        return userService.registerUser(dto);
    }

    // LOGIN API
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO dto){

        User user = userService.findByEmail(dto.getEmail());

        if(!user.getPassword().equals(dto.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }

        return "Login successful";
    }
}