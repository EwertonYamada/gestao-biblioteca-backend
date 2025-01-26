package com.gestao_biblioteca_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "usuarios")
@Setter
@Getter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Obrigatório informar o nome do usuário")
    private String nome;

    @Email(message = "Obrigatório informar um e-mail valido")
    @NotBlank(message = "Obrigatório informar o e-mail do usuário")
    private String email;

    @Column(name = "data_cadastro")
    @NotNull(message = "Obrigatório informar a data de cadastro do usuário")
    private Date dataCadastro;

    @NotBlank(message = "Obrigatório informar o telefone do usuário")
    private String telefone;
}
