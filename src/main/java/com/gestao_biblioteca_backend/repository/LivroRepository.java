package com.gestao_biblioteca_backend.repository;

import com.gestao_biblioteca_backend.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query(nativeQuery = true,
        value = " SELECT l.* " +
                " FROM livros l " +
                " WHERE l.categoria IN (" +
                "   SELECT DISTINCT li.categoria " +
                "   FROM livros li " +
                "   INNER JOIN emprestimos e ON " +
                "       e.livro_id = li.id " +
                "   WHERE e.usuario_id = :usuarioId) " +
                " AND l.id NOT IN ( " +
                "   SELECT em.livro_id " +
                "   FROM emprestimos em " +
                "   WHERE em.usuario_id = :usuarioId) " +
                " ORDER BY l.titulo ASC ")
    List<Livro> buscarRecomendacoes(@Param("usuarioId") Long usuarioId);

    @Query(nativeQuery = true,
            value = " SELECT l.* " +
                    " FROM livros l " +
                    " WHERE " +
                    "   l.id NOT IN ( " +
                    "   SELECT e.livro_id " +
                    "   FROM emprestimos e " +
                    "   WHERE e.status = 'ATIVO') " +
                    " ORDER BY l.titulo ASC")
    List<Livro> buscarLivrosDisponiveis();
}
