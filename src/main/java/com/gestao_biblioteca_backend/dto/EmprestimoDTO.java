package com.gestao_biblioteca_backend.dto;

import com.gestao_biblioteca_backend.enums.StatusEmprestimo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EmprestimoDTO {
    @NotNull
    public Long usuarioId;

    @Getter
    @NotNull
    public Long livroId;

    @NotNull
    public LocalDate dataEmprestimo;

    @NotNull
    public LocalDate dataDevolucao;

    public StatusEmprestimo status;

}
