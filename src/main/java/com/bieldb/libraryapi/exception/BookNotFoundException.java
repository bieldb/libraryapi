package com.bieldb.libraryapi.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super("Livro com ID " + id + " não foi encontrado.");
    }

    public BookNotFoundException(String isbn) {
        super("Livro com ISBN " + isbn + " não foi encontrado.");
    }
}
