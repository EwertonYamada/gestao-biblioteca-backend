package com.gestao_biblioteca_backend.dto;

import com.gestao_biblioteca_backend.enums.StatusEmprestimo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EmprestimoDTO {
    @NotNull
    private Long usuarioId;
    @NotNull
    private Long livroId;
    @NotNull
    private LocalDate dataEmprestimo;
    @NotNull
    private LocalDate dataDevolucao;
    @NotNull
    private StatusEmprestimo status;
}
