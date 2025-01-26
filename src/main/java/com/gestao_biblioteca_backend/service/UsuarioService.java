package com.gestao_biblioteca_backend.service;

import com.gestao_biblioteca_backend.model.Usuario;
import com.gestao_biblioteca_backend.repository.EmprestimoRepository;
import com.gestao_biblioteca_backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmprestimoRepository emprestimoRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, EmprestimoRepository emprestimoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    @Transactional
    public Usuario criarUsuario(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    @Transactional
    public void deletarUsuario(Long id) {
        if (!this.usuarioRepository.existsById(id)) throw new RuntimeException("Usuário não existente!");
        if (this.emprestimoRepository.verificarSeUsuarioPossuiEmprestimoAtivo(id)) throw new RuntimeException("Não é possível deletar um usuário que possui emprestimo ativo!");
        this.usuarioRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Usuario lerUsuario(Long id) {
        return this.buscarUsuarioPeloId(id);
    }

    public Usuario buscarUsuarioPeloId(Long usuarioId) {
        return this.usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @Transactional
    public Usuario atualizarUsuario(Usuario usuario, Long id) {
        return this.usuarioRepository.save(usuario);
    }
}
