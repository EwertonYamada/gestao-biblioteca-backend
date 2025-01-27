package com.gestao_biblioteca_backend.repository;

import com.gestao_biblioteca_backend.dto.EmprestimoListDTO;
import com.gestao_biblioteca_backend.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query(nativeQuery = true,
        value = " SELECT COUNT(*) > 0 " +
                " FROM emprestimos e " +
                " WHERE e.livro_id = :livroId " +
                "   AND e.status = 'ATIVO'")
    Boolean verificarSeLivroPossuiEmprestimoAtivo(@Param("livroId") Long livroId);

    @Query(nativeQuery = true,
        value = " SELECT COUNT(*) > 0 " +
                " FROM emprestimos e " +
                " WHERE e.usuario_id = :usuarioId" +
                "   AND e.status = 'ATIVO'")
    Boolean verificarSeUsuarioPossuiEmprestimoAtivo(@Param("usuarioId") Long usuarioId);

    @Query(nativeQuery = true,
        value = " SELECT " +
                "   e.id AS emprestimoId, " +
                "   l.titulo AS titulo, " +
                "   u.nome AS nomeUsuario, " +
                "   e.data_emprestimo AS dataEmprestimo, " +
                "   e.data_devolucao AS dataDevolucao, " +
                "   e.status AS status" +
                " FROM " +
                "   emprestimos e " +
                " INNER JOIN usuarios u ON " +
                "   u.id = e.usuario_id " +
                " INNER JOIN livros l ON " +
                "   l.id = e.livro_id " +
                " ORDER BY e.id DESC ")
    List<EmprestimoListDTO> buscarTodosEmprestimos();
}
