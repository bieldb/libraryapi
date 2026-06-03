package com.bieldb.libraryapi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bieldb.libraryapi.model.Loan;
import com.bieldb.libraryapi.model.LoanStatus;

public interface LoanRepository extends JpaRepository<Loan, Long>{
    List<Loan> findByStatus(LoanStatus status);
    List<Loan> findByDataDevolucaoPrevistaBefore(LocalDate data);
}
