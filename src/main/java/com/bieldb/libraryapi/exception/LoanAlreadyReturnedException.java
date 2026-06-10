package com.bieldb.libraryapi.exception;

public class LoanAlreadyReturnedException extends RuntimeException {
    public LoanAlreadyReturnedException(Long id) {
        super("O empréstimo com ID " + id + " já foi devolvido.");
    }
}
