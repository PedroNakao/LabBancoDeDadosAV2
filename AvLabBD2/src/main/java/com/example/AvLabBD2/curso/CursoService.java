package com.example.AvLabBD2.curso;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    public List<Curso> listarTodos() {
        return cursoRepository.listarTodosOrdenados();
    }

    public List<Curso> listarCursos(){
        return cursoRepository.listarTodosOrdenados();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public void salvar(Curso curso) {
        cursoRepository.save(curso);
    }

    public List<Curso> buscarPorNome(String nome) {
        return cursoRepository.buscarPorNome(nome);
    }

    public void salvarCurso(Curso curso) {
        this.cursoRepository.save(curso);
    }

    public Curso getCursoById(Long id) {
    return cursoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }

    public void deletar(Long id) {
        cursoRepository.deleteById(id);
    }

    public void deletarCurso(Long id) {
        this.cursoRepository.deleteById(id);
    }
}
