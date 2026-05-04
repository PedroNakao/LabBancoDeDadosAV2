package com.example.AvLabBD2.curso;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNome(String nome);

    @Query("SELECT c FROM Curso c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome,'%')) ORDER BY c.nome")
    List<Curso> buscarPorNome(@Param("nome") String nome);

    @Query(value = "SELECT * FROM Curso ORDER BY nome", nativeQuery = true)
    List<Curso> listarTodosOrdenados();
}
