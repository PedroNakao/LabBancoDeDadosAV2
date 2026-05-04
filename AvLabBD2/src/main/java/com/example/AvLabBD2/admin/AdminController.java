package com.example.AvLabBD2.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.AvLabBD2.aluno.AlunoService;
import com.example.AvLabBD2.curso.Curso;
import com.example.AvLabBD2.curso.CursoService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
    
    private final AdminService adminService;
    private final AlunoService alunoService;
    private final CursoService cursoService;

    @GetMapping("/cadastraTipo")
    public String telaLoginCurso(@RequestParam(required = false) String erro, Model model) {
        model.addAttribute("destino", "cadastraTipo");
        if (erro != null) model.addAttribute("erro", "Login ou senha inválida.");
        return "admin/login";
    }

    @GetMapping("/consultaCandidatos")
    public String telaLoginConsulta(@RequestParam(required = false) String erro, Model model){
        model.addAttribute("destino", "consultaCandidatos");
        if (erro != null) model.addAttribute("erro", "Login ou senha inválida.");
        return "admin/login";
    }

    @PostMapping("/admin/login")
    public String processarLogin(@RequestParam String login, @RequestParam String senha, @RequestParam String destino,HttpSession session) {
        if(adminService.autenticar(login, senha)) {
            session.setAttribute("adminLogado",true);
            return "redirect:/" + destino + "/area";
        }
        return "redirect:/" + destino + "?erro=true";
    }

    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/consultaCandidatos/area")
    public String consultaCandidatos(HttpSession session, Model model) {
        if (!isLogado(session)) return "redirect:/consultaCandidatos";
        model.addAttribute("alunos", alunoService.listarTodos());
        return "admin/consultaCandidatos";
    }

    @GetMapping("consultaCandidatos/porCurso")
    public String consultaPorCurso(@RequestParam String curso, HttpSession session, Model model) {
        if(!isLogado(session)) return "redirect:/consultaCandidatos";
        model.addAttribute("alunos", alunoService.buscarPorCurso(curso));
        model.addAttribute("filtro", curso);
        return "admin/consultaCandidatos";
    }

    @GetMapping("/consultaCandidatos/porBairro")
    public String consultaPorBairro(@RequestParam String bairro, HttpSession session, Model model) {
        if(!isLogado(session)) return "redirect:/consultaCandidatos";
        model.addAttribute("alunos", alunoService.buscarPorBairro(bairro));
        model.addAttribute("filtro", bairro);
        return "admin/consultaCandidatos";
    }

    @GetMapping("/consultaCandidatos/primeiros10")
    public String consultaPrimeiros10(HttpSession session, Model model) {
        if (!isLogado(session)) return "redirect:/consultaCandidatos";
        model.addAttribute("alunos", alunoService.primeiros10());
        model.addAttribute("filtro", "10 primeiros cadastrados");
        return "admin/consultaCandidatos";
    }

    @GetMapping("/consultaCandidatos/ultimos10")
    public String consultaUltimos10(HttpSession session, Model model) {
        if (!isLogado(session)) return "redirect:/consultaCandidatos";
        model.addAttribute("alunos", alunoService.ultimos10());
        model.addAttribute("filtro", "10 últimos cadastrados");
        return "admin/consultaCandidatos";
    }

    @GetMapping("/cadastraTipo/area")
    public String listaCursos(Model model) {
        model.addAttribute("cursos", cursoService.listarTodos());
        model.addAttribute("curso", new Curso());
        return "admin/cadastraTipo";
    }

    @PostMapping("/cadastraTipo/salvar")
    public String salvarCurso(Curso curso) {
        cursoService.salvar(curso);
        return "redirect:/cadastraTipo/area";
    }

    @GetMapping("/cadastraTipo/editar/{id}")
    public String editarCurso(@org.springframework.web.bind.annotation.PathVariable Long id, Model model) {
        cursoService.buscarPorId(id).ifPresent(c -> model.addAttribute("curso", c));
        model.addAttribute("cursos", cursoService.listarTodos());
        return "admin/cadastraTipo";
    }

    @GetMapping("/cadastraTipo/deletar/{id}")
    public String deletarCurso(@org.springframework.web.bind.annotation.PathVariable Long id) {
        cursoService.deletar(id);
        return "redirect:/cadastraTipo/area";
    }

    private boolean isLogado(HttpSession session) {
        return Boolean.TRUE.equals(session.getAttribute("adminLogado"));
    }
}
