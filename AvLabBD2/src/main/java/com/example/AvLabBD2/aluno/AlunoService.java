package com.example.AvLabBD2.aluno;

import java.time.LocalDate;
import java.time.LocalTime;

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
}