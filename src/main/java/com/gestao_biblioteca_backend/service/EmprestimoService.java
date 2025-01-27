package com.gestao_biblioteca_backend.service;

import com.gestao_biblioteca_backend.dto.EmprestimoDTO;
import com.gestao_biblioteca_backend.dto.EmprestimoListDTO;
import com.gestao_biblioteca_backend.enums.StatusEmprestimo;
import com.gestao_biblioteca_backend.model.Emprestimo;
import com.gestao_biblioteca_backend.model.Livro;
import com.gestao_biblioteca_backend.model.Usuario;
import com.gestao_biblioteca_backend.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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
        this.validacoes(emprestimoDTO);
        Livro livro = this.livroService.buscarLivroPeloId(emprestimoDTO.getLivroId());
        Usuario usuario = this.usuarioService.buscarUsuarioPeloId(emprestimoDTO.getUsuarioId());
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);
        emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
        emprestimo.setDataDevolucao(emprestimoDTO.getDataDevolucao());
        emprestimo.setStatus(StatusEmprestimo.ATIVO);
        return this.emprestimoRepository.save(emprestimo);
    }

    private void validacoes(EmprestimoDTO emprestimoDTO) {
        if (this.verificarSeLivroTemEmprestimoAtivo(emprestimoDTO.getLivroId())) throw new RuntimeException("O livro já possui um empréstimo ativo!");
        if (emprestimoDTO.getDataEmprestimo().isAfter(LocalDate.now())) throw new RuntimeException("A data de empréstimo não pode ser maior do que a atual!");
        if (emprestimoDTO.getDataDevolucao().isBefore(emprestimoDTO.getDataEmprestimo())) throw new RuntimeException("A data de devolução não pode ser menor do que a data de empréstimo!");
    }

    @Transactional
    public Emprestimo devolverLivro(Long emprestimoId) {
        Emprestimo emprestimo = this.emprestimoRepository.findById(emprestimoId).orElseThrow(() -> new RuntimeException("Empréstimo não encontrado!"));
        emprestimo.setDataDevolucao(LocalDate.now());
        emprestimo.setStatus(StatusEmprestimo.INATIVO);
        return this.emprestimoRepository.save(emprestimo);
    }

    @Transactional(readOnly = true)
    public List<EmprestimoListDTO> buscarTodosEmprestimos() {
        return this.emprestimoRepository.buscarTodosEmprestimos();
    }
}
