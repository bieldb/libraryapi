package com.bieldb.libraryapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bieldb.libraryapi.dto.BookCreateRequestDTO;
import com.bieldb.libraryapi.dto.BookResponseDTO;
import com.bieldb.libraryapi.exception.BookAlreadyExistsException;
import com.bieldb.libraryapi.exception.BookNotFoundException;
import com.bieldb.libraryapi.mapper.BookMapper;
import com.bieldb.libraryapi.model.Book;
import com.bieldb.libraryapi.repository.BookRepository;

@Service
public class BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public BookService(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    public BookResponseDTO create(BookCreateRequestDTO dto) {

        if (bookRepository.findByIsbn(dto.getIsbn()).isPresent()) {
            throw new BookAlreadyExistsException(dto.getIsbn());
        }

        Book book = bookMapper.toEntity(dto);
        book = bookRepository.save(book);
        return bookMapper.toDTO(book);
    }

    public BookResponseDTO getById(Long id) {
        Book book = bookRepository.findById(id)
        .orElseThrow(() -> new BookNotFoundException(id));
        return bookMapper.toDTO(book);
    }

    public List<BookResponseDTO> getAll() {
        List<Book> books = bookRepository.findAll();
        List<BookResponseDTO> dtos = new ArrayList<>();
        for (Book book : books) {
            dtos.add(bookMapper.toDTO(book));
        }
        return dtos;
    }

    public BookResponseDTO update(Long id, BookCreateRequestDTO dto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Book existingBook = bookRepository.findByIsbn(dto.getIsbn())
        .orElse(null);

        if (existingBook != null &&
                !existingBook.getId().equals(id)) {

            throw new BookAlreadyExistsException(dto.getIsbn());
        }
        book.setTitulo(dto.getTitulo());
        book.setAutor(dto.getAutor());
        book.setIsbn(dto.getIsbn());
        book.setQuantidadeDisponivel(dto.getQuantidadeInicial());
        book = bookRepository.save(book);
        return bookMapper.toDTO(book);
    }

    public void delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        bookRepository.delete(book);
    }

    public BookResponseDTO getByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
        return bookMapper.toDTO(book);
    }
}
