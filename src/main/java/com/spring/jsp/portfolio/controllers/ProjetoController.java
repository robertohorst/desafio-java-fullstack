package com.spring.jsp.portfolio.controllers;

import com.spring.jsp.portfolio.model.Projeto;
import com.spring.jsp.portfolio.model.StatusRisco;
import com.spring.jsp.portfolio.model.StatusProjeto;
import com.spring.jsp.portfolio.service.PessoaService;
import com.spring.jsp.portfolio.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@Validated
@RequestMapping({"/", "/projetos"})
public class ProjetoController {
    @Autowired
    private final ProjetoService projetoService;

    @Autowired
    private final PessoaService pessoaService;

    public ProjetoController(ProjetoService projetoService, PessoaService pessoaService) {
        this.projetoService = projetoService;
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public String carregarProjetos(Model model) {
        model.addAttribute("projetos", projetoService.getProjetos());
        return "projetos";
    }

    @GetMapping("/novo")
    public String carregarProjeto(Model model, Projeto projeto) {

        model.addAttribute("projeto", projeto);
        model.addAttribute("gerentes", pessoaService.getByCargo(true, false));
        model.addAttribute("funcionarios", pessoaService.getByCargo(false, true));

        model.addAttribute("listaStatus", StatusProjeto.values());
        model.addAttribute("listaRiscos", StatusRisco.values());

        return "projeto";
    }
    @GetMapping("/{id}")
    public String editarProjeto(@PathVariable("id") Long id, Model model) {
        Projeto projeto = projetoService.getProjeto(id);
        return carregarProjeto(model, projeto);
    }

    @PostMapping("/salvar")
    public String salvarProjeto(@ModelAttribute("projeto")  Projeto projeto, Model model, BindingResult bindingResult) {
        if (projeto.getGerente() == null) {
            bindingResult.addError(new FieldError("projeto", "gerente", "Selecione um gerente"));
            return carregarProjeto(model, projeto);
        }

        projetoService.salvar(projeto);

        carregarProjetos(model);
        return "redirect:/projetos";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerProjeto(@PathVariable("id") Long id) {
        Projeto projeto = projetoService.getProjeto(id);
        if(Arrays.asList(StatusProjeto.STATUS_NAO_EXCLUIR).contains(projeto.getStatus())){
            return new ResponseEntity<>("Não foi possível excluir o projeto", HttpStatus.FORBIDDEN);
        }

        projetoService.remover(projeto);

        return new ResponseEntity<>("Projeto removido", HttpStatus.OK);
    }
}
