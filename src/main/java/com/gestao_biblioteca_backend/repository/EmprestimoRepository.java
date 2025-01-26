package com.gestao_biblioteca_backend.repository;

import com.gestao_biblioteca_backend.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query(nativeQuery = true,
        value = " SELECT COUNT(*) > 0 " +
                " FROM emprestimo e " +
                " WHERE e.livro_id = :livroId " +
                "   AND e.status = 'ATIVO'")
    Boolean verificarSeLivroPossuiEmprestimoAtivo(@Param("livroId") Long livroId);

    @Query(nativeQuery = true,
        value = " SELECT COUNT(*) > 0 " +
                " FROM emprestimo e " +
                " WHERE e.usuario_id = :usuarioId" +
                "   AND e.status = 'ATIVO'")
    Boolean verificarSeUsuarioPossuiEmprestimoAtivo(@Param("usuarioId") Long usuarioId);

}
