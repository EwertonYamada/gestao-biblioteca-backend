package com.gestao_biblioteca_backend.controller;

import com.gestao_biblioteca_backend.dto.EmprestimoDTO;
import com.gestao_biblioteca_backend.model.Emprestimo;
import com.gestao_biblioteca_backend.service.EmprestimoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping("/emprestar-livro")
    public ResponseEntity<Emprestimo> emprestarLivro(@RequestBody EmprestimoDTO emprestimoDTO) {
        return ResponseEntity.ok(this.emprestimoService.emprestarLivro(emprestimoDTO));
    }

    @PutMapping("/devolver-livro/{emprestimoId}")
    public ResponseEntity<Emprestimo> devolverLivro(@PathVariable Long emprestimoId) {
        return ResponseEntity.ok(this.emprestimoService.devolverLivro(emprestimoId));
    }
}
