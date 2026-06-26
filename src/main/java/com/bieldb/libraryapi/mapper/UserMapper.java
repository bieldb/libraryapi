package com.bieldb.libraryapi.mapper;

import org.springframework.stereotype.Component;

import com.bieldb.libraryapi.dto.RegisterRequestDTO;
import com.bieldb.libraryapi.dto.UserResponseDTO;
import com.bieldb.libraryapi.model.Role;
import com.bieldb.libraryapi.model.User;

@Component
public class UserMapper {
    public User toEntityUser(RegisterRequestDTO dto) {
        User user = new User();
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole() != null ? dto.getRole() : Role.USER);
        return user;
    }

    public UserResponseDTO toDtoResponse(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setNome(user.getNome());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRole(user.getRole());
        return userResponseDTO;
    }
}