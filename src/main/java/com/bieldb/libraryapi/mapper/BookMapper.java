package com.bieldb.libraryapi.mapper;

import org.springframework.stereotype.Component;

import com.bieldb.libraryapi.dto.BookCreateRequestDTO;
import com.bieldb.libraryapi.dto.BookResponseDTO;
import com.bieldb.libraryapi.model.Book;

@Component
public class BookMapper {
    public Book toEntity(BookCreateRequestDTO dto) {
        Book book = new Book();
        book.setTitulo(dto.getTitulo());
        book.setAutor(dto.getAutor());
        book.setIsbn(dto.getIsbn());
        book.setQuantidadeDisponivel(dto.getQuantidadeInicial());
        return book;
    }

    public BookResponseDTO toDTO(Book book) {
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setId(book.getId());
        bookResponseDTO.setTitulo(book.getTitulo());
        bookResponseDTO.setAutor(book.getAutor());
        bookResponseDTO.setIsbn(book.getIsbn());
        bookResponseDTO.setQuantidadeDisponivel(book.getQuantidadeDisponivel());
        return bookResponseDTO;
    }
}
