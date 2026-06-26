package com.bieldb.libraryapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bieldb.libraryapi.dto.BookCreateRequestDTO;
import com.bieldb.libraryapi.dto.BookResponseDTO;
import com.bieldb.libraryapi.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public BookResponseDTO post(@Valid @RequestBody BookCreateRequestDTO dto) {
        return bookService.create(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public List<BookResponseDTO> findAll() {
        return bookService.getAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{id}")
    public BookResponseDTO findById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/isbn/{isbn}")
    public BookResponseDTO findByIsbn(@PathVariable String isbn) {
        return bookService.getByIsbn(isbn);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public BookResponseDTO update(@Valid @RequestBody BookCreateRequestDTO dto, @PathVariable Long id) {
        return bookService.update(id, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}