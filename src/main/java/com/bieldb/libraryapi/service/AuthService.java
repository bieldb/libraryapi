package com.bieldb.libraryapi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bieldb.libraryapi.dto.AuthResponseDTO;
import com.bieldb.libraryapi.dto.LoginRequestDTO;
import com.bieldb.libraryapi.dto.RegisterRequestDTO;
import com.bieldb.libraryapi.dto.UserResponseDTO;
import com.bieldb.libraryapi.exception.InvalidCredentialsException;
import com.bieldb.libraryapi.exception.UserAlreadyExistsException;
import com.bieldb.libraryapi.exception.UserNotFoundException;
import com.bieldb.libraryapi.mapper.UserMapper;
import com.bieldb.libraryapi.model.User;
import com.bieldb.libraryapi.repository.UserRepository;

@Service
public class AuthService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthService(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    
    public UserResponseDTO register(RegisterRequestDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(dto.getEmail());
        }
        User user = userMapper.toEntityUser(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user = userRepository.save(user);
        return userMapper.toDtoResponse(user);
    }

    public AuthResponseDTO login(LoginRequestDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new UserNotFoundException(dto.getEmail()));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        };
        String token = jwtService.generateToken(user);
        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        return response;
    }
}
