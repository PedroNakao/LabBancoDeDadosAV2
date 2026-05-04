package com.example.AvLabBD2.aluno;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByCursoEscolhidoContainingIgnoreCase(String curso);
    List<Aluno> findByBairroContainingIgnoreCase(String bairro);
    List<Aluno> findAllByOrderByCursoEscolhidoAsc();

    @Query("SELECT a FROM Aluno a ORDER BY a.bairro ASC")
    List<Aluno> listarOrdenadosPorBairro();

    @Query("SELECT a FROM Aluno a ORDER BY a.id ASC")
    List<Aluno> primeiros10(Pageable pageable);

    @Query("SELECT a FROM Aluno a ORDER BY a.id DESC")
    List<Aluno> ultimos10(Pageable pageable);
}
