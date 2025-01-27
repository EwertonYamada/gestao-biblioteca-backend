package com.gestao_biblioteca_backend.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class EmprestimoListDTO {
    private Long emprestimoId;
    private String titulo;
    private String nomeUsuario;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private String status;

    public EmprestimoListDTO(Long emprestimoId, String titulo, String nomeUsuario, Date dataEmprestimo, Date dataDevolucao, String status) {
        this.emprestimoId = emprestimoId;
        this.titulo = titulo;
        this.nomeUsuario = nomeUsuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }
}
