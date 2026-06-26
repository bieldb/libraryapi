package com.bieldb.libraryapi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bieldb.libraryapi.dto.LoanRequestDTO;
import com.bieldb.libraryapi.dto.LoanResponseDTO;
import com.bieldb.libraryapi.exception.BookNotFoundException;
import com.bieldb.libraryapi.exception.BookUnavailableException;
import com.bieldb.libraryapi.exception.LoanAlreadyReturnedException;
import com.bieldb.libraryapi.exception.LoanNotFoundException;
import com.bieldb.libraryapi.mapper.LoanMapper;
import com.bieldb.libraryapi.model.Loan;
import com.bieldb.libraryapi.model.LoanStatus;
import com.bieldb.libraryapi.model.Book;
import com.bieldb.libraryapi.repository.BookRepository;
import com.bieldb.libraryapi.repository.LoanRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanService {
    private final LoanMapper loanMapper;
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    private Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public LoanService(LoanMapper loanMapper, LoanRepository loanRepository,
            BookRepository bookRepository) {
        this.loanMapper = loanMapper;
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public LoanResponseDTO create(LoanRequestDTO dto) {
        Book book = findBookById(dto.getBookId());
        if (book.getQuantidadeDisponivel() <= 0) {
            throw new BookUnavailableException(book.getId());
        }
        book.setQuantidadeDisponivel(book.getQuantidadeDisponivel() - 1);
        bookRepository.save(book);
        Loan loan = loanMapper.toEntityLoan(dto);
        loan.setDataEmprestimo(LocalDate.now());
        loan.setStatus(LoanStatus.EMPRESTADO);
        loan.setBook(book);
        loan = loanRepository.save(loan);
        return loanMapper.toDTOLoan(loan);
    }

    public LoanResponseDTO getById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));
        return loanMapper.toDTOLoan(loan);
    }

    public List<LoanResponseDTO> getAll() {
        List<Loan> loans = loanRepository.findAll();
        List<LoanResponseDTO> dtos = new ArrayList<>();
        for (Loan loan : loans) {
            dtos.add(loanMapper.toDTOLoan(loan));
        }
        return dtos;
    }

    public List<LoanResponseDTO> getByStatus(LoanStatus status) {
        List<Loan> loans = loanRepository.findByStatus(status);
        List<LoanResponseDTO> dtos = new ArrayList<>();
        for (Loan loan : loans) {
            dtos.add(loanMapper.toDTOLoan(loan));
        }
        return dtos;
    }

    public List<LoanResponseDTO> getAtrasados() {
        List<Loan> loans = loanRepository.findByDataDevolucaoPrevistaBeforeAndStatusNot(LocalDate.now(),
                LoanStatus.DEVOLVIDO);
        List<LoanResponseDTO> dtos = new ArrayList<>();
        for (Loan loan : loans) {
            dtos.add(loanMapper.toDTOLoan(loan));
        }
        return dtos;
    }

    @Transactional
    public LoanResponseDTO devolverLivro(Long id) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));
        if (loan.getStatus() == LoanStatus.DEVOLVIDO) {
            throw new LoanAlreadyReturnedException(id);
        }
        Book book = loan.getBook();
        book.setQuantidadeDisponivel(book.getQuantidadeDisponivel() + 1);
        loan.setStatus(LoanStatus.DEVOLVIDO);
        loan.setDataDevolucaoReal(LocalDate.now());
        loanRepository.save(loan);
        bookRepository.save(book);
        return loanMapper.toDTOLoan(loan);
    }
}
