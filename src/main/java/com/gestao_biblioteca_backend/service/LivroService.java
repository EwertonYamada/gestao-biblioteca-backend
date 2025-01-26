package com.gestao_biblioteca_backend.service;

import com.gestao_biblioteca_backend.model.Livro;
import com.gestao_biblioteca_backend.repository.EmprestimoRepository;
import com.gestao_biblioteca_backend.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final EmprestimoRepository emprestimoRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository, EmprestimoRepository emprestimoRepository) {
        this.livroRepository = livroRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    @Transactional
    public Livro criarLivro(Livro livro) {
        return this.livroRepository.save(livro);
    }

    @Transactional
    public void deletarLivro(Long id) {
        if (!this.livroRepository.existsById(id)) throw new RuntimeException("Livro não existente!");
        if (this.emprestimoRepository.verificarSeLivroPossuiEmprestimoAtivo(id)) throw new RuntimeException("Não é possível deletar um livro que está emprestado!");
        this.livroRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Livro lerLivro(Long livroId) {
        return this.buscarLivroPeloId(livroId);
    }

    public Livro buscarLivroPeloId(Long livroId) {
        return this.livroRepository.findById(livroId).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    @Transactional
    public Livro atualizarLivro(Livro livro) {
        return this.livroRepository.save(livro);
    }
}
