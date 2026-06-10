package com.bieldb.libraryapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bieldb.libraryapi.dto.LoanRequestDTO;
import com.bieldb.libraryapi.dto.LoanResponseDTO;
import com.bieldb.libraryapi.model.LoanStatus;
import com.bieldb.libraryapi.service.LoanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }
    
    @PostMapping
    public LoanResponseDTO create(@Valid @RequestBody LoanRequestDTO dto) {
        return loanService.create(dto);
    }

    @GetMapping
    public List<LoanResponseDTO> findAll() {
        return loanService.getAll();
    }

    @GetMapping("{id}")
    public LoanResponseDTO findById(@PathVariable Long id) {
        return loanService.getById(id);
    }

    @GetMapping("/status/{status}")
    public List<LoanResponseDTO> findByStatus(@PathVariable LoanStatus status) {
        return loanService.getByStatus(status);
    }

    @GetMapping("/atrasados")
    public List<LoanResponseDTO> findByAtrasado() {
        return loanService.getAtrasados();
    }

    @PutMapping("{id}/devolver")
    public LoanResponseDTO devolverLivro(@PathVariable Long id) {
        return loanService.devolverLivro(id);
    }
}
