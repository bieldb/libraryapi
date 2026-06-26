package com.bieldb.libraryapi.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException (String email) {
        super("Já existe um cadastro com esse email: " + email);
    }
}
