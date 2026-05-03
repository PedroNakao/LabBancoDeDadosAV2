package com.example.AvLabBD2.aluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aluno")
public class AlunoController {
    
    @Autowired
    private AlunoService alunoService;

    @GetMapping("/PaginaCadastroAluno")
    public String carregarPaginaCadastroAluno(Model model) {
        Aluno aluno = new Aluno();
        model.addAttribute("aluno", aluno);
        return "cadastroAluno";
    }

    @PostMapping("/CadastrarAluno")
    public String salvarAluno (@ModelAttribute("aluno") Aluno aluno) {
        alunoService.salvarAluno(aluno);
        return "redirect:/aluno/PaginaCadastroAluno";
    }
}
