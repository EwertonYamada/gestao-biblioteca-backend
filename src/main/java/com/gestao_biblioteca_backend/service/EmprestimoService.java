package com.gestao_biblioteca_backend.service;

import com.gestao_biblioteca_backend.dto.EmprestimoDTO;
import com.gestao_biblioteca_backend.enums.StatusEmprestimo;
import com.gestao_biblioteca_backend.model.Emprestimo;
import com.gestao_biblioteca_backend.model.Livro;
import com.gestao_biblioteca_backend.model.Usuario;
import com.gestao_biblioteca_backend.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioService usuarioService;
    private final LivroService livroService;

    public EmprestimoService(EmprestimoRepository emprestimoRepository,
                             UsuarioService usuarioService,
                             LivroService livroService) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioService = usuarioService;
        this.livroService = livroService;
    }

    public boolean verificarSeLivroTemEmprestimoAtivo(Long livroId) {
        return this.emprestimoRepository.verificarSeLivroPossuiEmprestimoAtivo(livroId);
    }

    @Transactional
    public Emprestimo emprestarLivro(EmprestimoDTO emprestimoDTO) {
        if (this.verificarSeLivroTemEmprestimoAtivo(emprestimoDTO.getLivroId())) throw new RuntimeException("O livro já possui um empréstimo ativo!");
        Livro livro = this.livroService.buscarLivroPeloId(emprestimoDTO.getLivroId());
        Usuario usuario = this.usuarioService.buscarUsuarioPeloId(emprestimoDTO.getUsuarioId());
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);
        emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
        emprestimo.setDataDevolucao(emprestimoDTO.getDataDevolucao());
        emprestimo.setStatus(emprestimoDTO.getStatus());
        return this.emprestimoRepository.save(emprestimo);

//        return this.emprestimoRepository.save(Emprestimo.builder()
//                .livro(livro)
//                .usuario(usuario)
//                .dataEmprestimo(emprestimoDTO.getDataEmprestimo())
//                .dataDevolucao(emprestimoDTO.getDataDevolucao())
//                .status(emprestimoDTO.getStatus())
//                .build());
    }

    @Transactional
    public Emprestimo devolverLivro(Long emprestimoId) {
        Emprestimo emprestimo = this.emprestimoRepository.findById(emprestimoId).orElseThrow(() -> new RuntimeException("Empréstimo não encontrado!"));
        emprestimo.setDataDevolucao(LocalDate.now());
        emprestimo.setStatus(StatusEmprestimo.INATIVO);
        return this.emprestimoRepository.save(emprestimo);
    }
}
