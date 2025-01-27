package com.gestao_biblioteca_backend.dto;

import com.gestao_biblioteca_backend.enums.StatusEmprestimo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EmprestimoDTO {
    @NotNull(message = "Obrigatório informar o id do usuário")
    public Long usuarioId;

    @Getter
    @NotNull(message = "Obrigatório informar id do livro")
    public Long livroId;

    @NotNull(message = "Obrigatório informar data do empréstimo")
    public LocalDate dataEmprestimo;

    @NotNull(message = "Obrigatório informar data de devolução")
    public LocalDate dataDevolucao;

    public StatusEmprestimo status;

}
