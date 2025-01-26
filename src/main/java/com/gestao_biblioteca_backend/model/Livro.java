package com.gestao_biblioteca_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "livros")
@Getter
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Obrigatório informar o título do livro")
    private String titulo;

    @NotBlank(message = "Obrigatório informar o autor do livro")
    private String autor;

    @NotBlank(message = "Obrigatório informar o ISBN do livro")
    private String isbn;

    @Column(name = "data_publicacao")
    @NotNull(message = "Obrigatório informar a data de publicação do livro")
    private LocalDate dataPublicacao;

    @NotBlank(message = "Obrigatório informar a categoria do livro")
    private String categoria;

}
