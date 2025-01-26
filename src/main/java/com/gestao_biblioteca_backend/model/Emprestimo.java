package com.gestao_biblioteca_backend.model;

import com.gestao_biblioteca_backend.enums.StatusEmprestimo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "emprestimos")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;
    @Column(name = "data_emprestimo")
    private LocalDate dataEmprestimo;
    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;
    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;
}
