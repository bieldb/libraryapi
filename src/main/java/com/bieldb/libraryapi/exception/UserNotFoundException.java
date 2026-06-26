package com.bieldb.libraryapi.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super("O email não foi cadastrado: " + email);
    }
}
