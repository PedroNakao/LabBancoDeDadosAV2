package com.example.AvLabBD2.aluno;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlunoService {
    
    private final AlunoRepository alunoRepository;

    public void salvarAluno(Aluno aluno) {
        aluno.setDataCadastrado(LocalDate.now());
        aluno.setHoraCadastrado(LocalTime.now().withNano(0));
        alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAllByOrderByCursoEscolhidoAsc();
    }

    public List<Aluno> buscarPorCurso(String curso) {
        return alunoRepository.findByCursoEscolhidoContainingIgnoreCase(curso);
    }

    public List<Aluno> buscarPorBairro(String bairro) {
        return alunoRepository.findByBairroContainingIgnoreCase(bairro);
    }

    public List<Aluno> primeiros10() {
    return alunoRepository.primeiros10(PageRequest.of(0, 10));
    }
    public List<Aluno> ultimos10() {
    return alunoRepository.ultimos10(PageRequest.of(0, 10));
}
}