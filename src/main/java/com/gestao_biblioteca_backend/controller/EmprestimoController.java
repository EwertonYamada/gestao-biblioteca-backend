package com.gestao_biblioteca_backend.controller;

import com.gestao_biblioteca_backend.dto.EmprestimoDTO;
import com.gestao_biblioteca_backend.model.Emprestimo;
import com.gestao_biblioteca_backend.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping("/emprestar-livro")
    public ResponseEntity<Emprestimo> emprestarLivro(@Valid @RequestBody EmprestimoDTO emprestimoDTO) {
        return ResponseEntity.ok(this.emprestimoService.emprestarLivro(emprestimoDTO));
    }

    @PutMapping("/devolver-livro/{emprestimoId}")
    public ResponseEntity<Emprestimo> devolverLivro(@PathVariable Long emprestimoId) {
        return ResponseEntity.ok(this.emprestimoService.devolverLivro(emprestimoId));
    }

    @GetMapping("/buscar-todos")
    public ResponseEntity<List<Emprestimo>> buscarTodosEmprestimos() {
        return ResponseEntity.ok(this.emprestimoService.buscarTodosEmprestimos());
    }
}
