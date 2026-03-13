package com.example.cadastro.controller;

import com.example.cadastro.model.Aluno;
import com.example.cadastro.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alunos", service.listarTodos());
        return "alunos/lista"; // Vai procurar o arquivo lista.html na pasta templates/alunos
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "alunos/formulario"; // Vai procurar o arquivo formulario.html
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return service.buscarPorId(id)
                .map(aluno -> {
                    model.addAttribute("aluno", aluno);
                    return "alunos/formulario";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("mensagemErro", "Aluno não encontrado!");
                    return "redirect:/alunos";
                });
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("aluno") Aluno aluno, BindingResult result, RedirectAttributes redirectAttributes) {
        // Se houver erro de validação, volta para a tela do formulário
        if (result.hasErrors()) {
            return "alunos/formulario";
        }

        boolean isNovo = aluno.getId() == null;
        service.salvar(aluno);

        redirectAttributes.addFlashAttribute("mensagemSucesso",
                isNovo ? "Aluno cadastrado com sucesso!" : "Dados do aluno atualizados!");

        return "redirect:/alunos";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        service.excluir(id);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Aluno excluído com sucesso!");
        return "redirect:/alunos";
    }
}
