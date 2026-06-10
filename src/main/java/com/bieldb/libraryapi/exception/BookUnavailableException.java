package com.bieldb.libraryapi.exception;

public class BookUnavailableException extends RuntimeException {
    public BookUnavailableException(Long Id) {
        super("Não existem exemplares disponíveis para o livro " + Id);
    }
}
