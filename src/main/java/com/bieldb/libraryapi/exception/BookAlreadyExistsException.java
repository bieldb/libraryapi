package com.bieldb.libraryapi.exception;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException (String isbn) {
        super("Já existe um livro cadastrado com ISBN: " + isbn);
    }
}
