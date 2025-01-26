package com.gestao_biblioteca_backend.controller;

import com.gestao_biblioteca_backend.model.Usuario;
import com.gestao_biblioteca_backend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/criar-usuario")
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(this.usuarioService.criarUsuario(usuario));
    }

    @DeleteMapping("/deletar-usuario/{id}")
    public ResponseEntity<Void> deletarLivro (@PathVariable Long id) {
        this.usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ler-usuario/{id}")
    public ResponseEntity<Usuario> lerUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(this.usuarioService.lerUsuario(id));
    }

    @PutMapping("/atualizar-usuario/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@Valid @RequestBody Usuario usuario, @PathVariable Long id) {
        return ResponseEntity.ok(this.usuarioService.atualizarUsuario(usuario, id));
    }

    @GetMapping("/buscar-todos")
    public ResponseEntity<List<Usuario>> buscarTodosLivros() {
        return ResponseEntity.ok(this.usuarioService.buscarTodosUsuarios());
    }
}
