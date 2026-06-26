package com.bieldb.libraryapi.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Senha incorreta");
    }
}
