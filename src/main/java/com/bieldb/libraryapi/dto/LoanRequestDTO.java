package com.bieldb.libraryapi.dto;

import java.time.LocalDate;

import com.bieldb.libraryapi.model.LoanStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoanRequestDTO {
    @NotBlank(message = "O nome do leitor é obrigatório")
    private String nomeLeitor;
    @NotNull(message = "A data de devolução prevista é obrigatória")
    private LocalDate dataDevolucaoPrevista;
    @NotNull(message = "A data de empréstimo é obrigatória")
    private LocalDate dataEmprestimo;
    @NotNull(message = "O status é obrigatório")
    private LoanStatus status;
    @NotNull(message = "O livro é obrigatório")
    private Long bookId;
    
    public String getNomeLeitor() {
        return nomeLeitor;
    }
    public void setNomeLeitor(String nomeLeitor) {
        this.nomeLeitor = nomeLeitor;
    }
    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }
    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    public LoanStatus getStatus() {
        return status;
    }
    public void setStatus(LoanStatus status) {
        this.status = status;
    }
    
}
