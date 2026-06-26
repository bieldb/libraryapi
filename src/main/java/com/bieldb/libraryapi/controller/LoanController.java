package com.bieldb.libraryapi.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
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

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public LoanResponseDTO create(@Valid @RequestBody LoanRequestDTO dto) {
        return loanService.create(dto);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<LoanResponseDTO> findAll() {
        return loanService.getAll();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("{id}")
    public LoanResponseDTO findById(@PathVariable Long id) {
        return loanService.getById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/status/{status}")
    public List<LoanResponseDTO> findByStatus(@PathVariable LoanStatus status) {
        return loanService.getByStatus(status);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/atrasados")
    public List<LoanResponseDTO> findByAtrasado() {
        return loanService.getAtrasados();
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("{id}/devolver")
    public LoanResponseDTO devolverLivro(@PathVariable Long id) {
        return loanService.devolverLivro(id);
    }
}