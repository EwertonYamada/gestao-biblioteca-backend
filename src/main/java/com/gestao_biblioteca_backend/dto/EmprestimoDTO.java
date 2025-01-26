package com.gestao_biblioteca_backend.dto;

import com.gestao_biblioteca_backend.enums.StatusEmprestimo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//@Getter
//@Setter
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

    public @NotNull Long getUsuarioId() {
        return usuarioId;
    }

    public @NotNull Long getLivroId() {
        return livroId;
    }

    public @NotNull LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public @NotNull LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public @NotNull StatusEmprestimo getStatus() {
        return status;
    }
}
