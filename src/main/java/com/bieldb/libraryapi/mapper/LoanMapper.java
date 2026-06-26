package com.bieldb.libraryapi.mapper;

import org.springframework.stereotype.Component;

import com.bieldb.libraryapi.dto.LoanRequestDTO;
import com.bieldb.libraryapi.dto.LoanResponseDTO;
import com.bieldb.libraryapi.model.Loan;

@Component
public class LoanMapper {
    public Loan toEntityLoan(LoanRequestDTO dto) {
        Loan loan = new Loan();
        loan.setNomeLeitor(dto.getNomeLeitor());
        loan.setDataDevolucaoPrevista(dto.getDataDevolucaoPrevista());
        return loan;
    }

    public LoanResponseDTO toDTOLoan(Loan loan) {
        LoanResponseDTO loanResponseDTO = new LoanResponseDTO();
        loanResponseDTO.setId(loan.getId());
        loanResponseDTO.setNomeLeitor(loan.getNomeLeitor());
        loanResponseDTO.setDataEmprestimo(loan.getDataEmprestimo());
        loanResponseDTO.setDataDevolucaoPrevista(loan.getDataDevolucaoPrevista());
        loanResponseDTO.setDataDevolucaoReal(loan.getDataDevolucaoReal());
        loanResponseDTO.setStatus(loan.getStatus());
        loanResponseDTO.setTituloLivro(loan.getBook().getTitulo());
        return loanResponseDTO;
    }
}
