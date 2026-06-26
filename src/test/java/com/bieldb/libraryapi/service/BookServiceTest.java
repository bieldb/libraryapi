package com.bieldb.libraryapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bieldb.libraryapi.dto.BookCreateRequestDTO;
import com.bieldb.libraryapi.dto.BookResponseDTO;
import com.bieldb.libraryapi.mapper.BookMapper;
import com.bieldb.libraryapi.model.Book;
import com.bieldb.libraryapi.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    @Test
    void shouldCreateBookWhenIsbnNotExists() {
        BookCreateRequestDTO request = new BookCreateRequestDTO();
        request.setTitulo("Clean Code");
        request.setAutor("Robert C. Martin");
        request.setIsbn("9788539000001");
        request.setQuantidadeInicial(3);

        Book bookEntity = new Book();
        bookEntity.setTitulo(request.getTitulo());
        bookEntity.setAutor(request.getAutor());
        bookEntity.setIsbn(request.getIsbn());
        bookEntity.setQuantidadeDisponivel(request.getQuantidadeInicial());

        Book savedBook = new Book();
        savedBook.setId(1L);
        savedBook.setTitulo(request.getTitulo());
        savedBook.setAutor(request.getAutor());
        savedBook.setIsbn(request.getIsbn());
        savedBook.setQuantidadeDisponivel(request.getQuantidadeInicial());

        BookResponseDTO responseDTO = new BookResponseDTO();
        responseDTO.setId(savedBook.getId());
        responseDTO.setTitulo(savedBook.getTitulo());
        responseDTO.setAutor(savedBook.getAutor());
        responseDTO.setIsbn(savedBook.getIsbn());
        responseDTO.setQuantidadeDisponivel(savedBook.getQuantidadeDisponivel());

        when(bookRepository.findByIsbn(request.getIsbn())).thenReturn(Optional.empty());
        when(bookMapper.toEntityBook(request)).thenReturn(bookEntity);
        when(bookRepository.save(bookEntity)).thenReturn(savedBook);
        when(bookMapper.toDTOBook(savedBook)).thenReturn(responseDTO);

        BookResponseDTO result = bookService.create(request);

        assertEquals(1L, result.getId());
        assertEquals("Clean Code", result.getTitulo());
        assertEquals("Robert C. Martin", result.getAutor());
        assertEquals("9788539000001", result.getIsbn());
        assertEquals(3, result.getQuantidadeDisponivel());

        verify(bookRepository).save(bookEntity);
    }
}
