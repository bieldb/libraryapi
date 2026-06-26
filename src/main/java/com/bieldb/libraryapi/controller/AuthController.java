package com.bieldb.libraryapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bieldb.libraryapi.dto.AuthResponseDTO;
import com.bieldb.libraryapi.dto.LoginRequestDTO;
import com.bieldb.libraryapi.dto.RegisterRequestDTO;
import com.bieldb.libraryapi.dto.UserResponseDTO;
import com.bieldb.libraryapi.service.AuthService;
import com.bieldb.libraryapi.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody RegisterRequestDTO dto) {
        return authService.register(dto);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginRequestDTO dto) {
        return authService.login(dto);
    }

    @GetMapping("/test-token")
    public String testToken(
            @RequestParam String token) {

        return jwtService.extractUsername(token);
    }
}
