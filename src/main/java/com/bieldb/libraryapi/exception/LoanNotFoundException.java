package com.bieldb.libraryapi.exception;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(Long id) {
        super("Emprestimo com ID " + id + " não foi encontrado.");
    }
}
