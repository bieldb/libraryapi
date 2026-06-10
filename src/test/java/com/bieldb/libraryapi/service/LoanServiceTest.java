package com.bieldb.libraryapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bieldb.libraryapi.dto.LoanResponseDTO;
import com.bieldb.libraryapi.mapper.LoanMapper;
import com.bieldb.libraryapi.model.Book;
import com.bieldb.libraryapi.model.Loan;
import com.bieldb.libraryapi.model.LoanStatus;
import com.bieldb.libraryapi.repository.BookRepository;
import com.bieldb.libraryapi.repository.LoanRepository;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanMapper loanMapper;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LoanService loanService;

    @Test
    void shouldReturnLoanAfterDevolution() {
        Book book = new Book();
        book.setId(10L);
        book.setQuantidadeDisponivel(0);

        Loan loan = new Loan();
        loan.setId(20L);
        loan.setStatus(LoanStatus.EMPRESTADO);
        loan.setBook(book);
        loan.setDataEmprestimo(LocalDate.of(2026, 6, 1));

        LoanResponseDTO responseDTO = new LoanResponseDTO();
        responseDTO.setId(loan.getId());
        responseDTO.setStatus(LoanStatus.DEVOLVIDO);
        responseDTO.setTituloLivro("Domain-Driven Design");

        when(loanRepository.findById(loan.getId())).thenReturn(Optional.of(loan));
        when(loanMapper.toDTO(loan)).thenReturn(responseDTO);
        when(loanRepository.save(loan)).thenReturn(loan);
        when(bookRepository.save(book)).thenReturn(book);

        LoanResponseDTO result = loanService.devolverLivro(loan.getId());

        assertEquals(LoanStatus.DEVOLVIDO, result.getStatus());
        assertEquals(1, book.getQuantidadeDisponivel());

        verify(loanRepository).save(loan);
        verify(bookRepository).save(book);
    }
}
