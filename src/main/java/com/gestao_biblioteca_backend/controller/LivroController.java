package com.gestao_biblioteca_backend.controller;

import com.gestao_biblioteca_backend.model.Livro;
import com.gestao_biblioteca_backend.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping("/criar-livro")
    public ResponseEntity<Livro> criarLivro(@Valid @RequestBody Livro livro) {
        return ResponseEntity.ok(this.livroService.criarLivro(livro));
    }

    @DeleteMapping("/deletar-livro/{id}")
    public ResponseEntity<HttpStatus> deletarLivro (@PathVariable Long id) {
        this.livroService.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ler-livro/{id}")
    public ResponseEntity<Livro> lerLivro(@PathVariable Long id) {
        return ResponseEntity.ok(this.livroService.lerLivro(id));
    }

    @PutMapping("/atualizar-livro")
    public ResponseEntity<Livro> atualizarLivro(@Valid @RequestBody Livro livro) {
        return ResponseEntity.ok(this.livroService.atualizarLivro(livro));
    }

    @GetMapping("/buscar-recomendacoes/{usuarioId}")
    public ResponseEntity<List<Livro>> buscarRecomendacoes(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(this.livroService.buscarRecomendacoes(usuarioId));
    }

    @GetMapping("/buscar-titulo-google-books/{titulo}")
    public ResponseEntity<List<Livro>> buscarNoGoogleBooks(@PathVariable String titulo) {
        return ResponseEntity.ok(this.livroService.buscarNoGoogleBooks(titulo));
    }

    @GetMapping("/buscar-todos")
    public ResponseEntity<List<Livro>> buscarTodosLivros() {
        return ResponseEntity.ok(this.livroService.buscarTodosLivros());
    }

    @GetMapping("/buscar-livros-disponiveis")
    public ResponseEntity<List<Livro>> buscarTodosDisponiveis() {
        return ResponseEntity.ok(this.livroService.buscarTodosDisponiveis());
    }
}
