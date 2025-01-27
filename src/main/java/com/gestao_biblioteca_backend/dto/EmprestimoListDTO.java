package com.gestao_biblioteca_backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmprestimoListDTO {
    private Long emprestimoId;
    private String titulo;
    private String nomeUsuario;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public EmprestimoListDTO(Long emprestimoId, String titulo, String nomeUsuario, Date dataEmprestimo, Date dataDevolucao) {
        this.emprestimoId = emprestimoId;
        this.titulo = titulo;
        this.nomeUsuario = nomeUsuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }
}
