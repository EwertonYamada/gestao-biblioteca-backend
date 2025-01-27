package com.gestao_biblioteca_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Obrigatório informar o nome do usuário")
    private String nome;

    @Email
    @NotBlank(message = "Obrigatório informar o e-mail do usuário")
    private String email;

    @Column(name = "data_cadastro")
    @NotNull(message = "Obrigatório informar a data de cadastro do usuário")
    private LocalDate dataCadastro;

    @NotBlank(message = "Obrigatório informar o telefone do usuário")
    private String telefone;
}
