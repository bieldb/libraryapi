package com.bieldb.libraryapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookCreateRequestDTO {
    @NotBlank(message = "O título é obrigatório")
    private String titulo;
    @NotBlank(message = "O autor é obrigatório")
    private String autor;
    @NotBlank(message = "O ISBN é obrigatório")
    private String isbn;
    @NotNull(message = "A quantidade inicial é obrigatória")
    @Min(value = 1, message = "Deve existir pelo menos 1 exemplar")
    private Integer quantidadeInicial;
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public Integer getQuantidadeInicial() {
        return quantidadeInicial;
    }
    public void setQuantidadeInicial(Integer quantidadeInicial) {
        this.quantidadeInicial = quantidadeInicial;
    }
    
}
