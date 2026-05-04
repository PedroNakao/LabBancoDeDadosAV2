package com.example.AvLabBD2.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String carregarPaginaCurso(Model model) {
        model.addAttribute("listaCurso", cursoService.listarCursos());
        return "CursoPagina";
    }

    @GetMapping("/getCadastroCurso")
    public String carrefarPaginaCadastroCurso(Model model) {
        Curso curso = new Curso();
        model.addAttribute("curso", curso);
        return "CursoPaginaCadastro";
    }

    @PostMapping("/postCadastroCurso")
    public String salvarCurso (@ModelAttribute("curso") Curso curso) {
        cursoService.salvarCurso(curso);
        return "redirect:/curso";
    }

    @GetMapping("/getAtualizarCurso/{id}")
    public String carregarPaginaAtualizarCurso(@PathVariable(value = "id") long id, Model model) {
        Curso curso = cursoService.getCursoById(id);
        model.addAttribute("curso", curso);
        return "CursoPaginaAtualizar";
    }

    @PostMapping("/postAtualizarCurso")
    public String atualizarCurso(@ModelAttribute("curso") Curso curso){
        cursoService.salvarCurso(curso);
        return "redirect:/curso";
    }

    @GetMapping("/getDeletarCurso/{id}")
    public String deletarCurso(@PathVariable long id) {
        cursoService.deletarCurso(id);
        return "redirect:/curso";
    }
}
