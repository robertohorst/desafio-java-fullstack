package com.spring.jsp.portfolio.controllers;

import com.spring.jsp.portfolio.model.Pessoa;
import com.spring.jsp.portfolio.model.Projeto;
import com.spring.jsp.portfolio.model.StatusProjeto;
import com.spring.jsp.portfolio.service.ProjetoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@SpringBootTest
public class ProjetoControllerTest {

    @MockBean
    ProjetoService projetoService;

    @Autowired
    ProjetoController projetoController;

    Model model = Mockito.mock(Model.class);
    BindingResult bindingResult = Mockito.mock(BindingResult.class);

    @Test
    public void salvar_projeto(){
        Projeto projeto = getProjeto(StatusProjeto.EM_ANALISE);
        Pessoa gerente = new Pessoa();
        gerente.setId(10L);
        projeto.setGerente(gerente);

        Mockito.when(projetoService.getProjeto(1L)).thenReturn(projeto);

        projetoController.salvarProjeto(projeto, model, bindingResult);

        Mockito.verify(projetoService, Mockito.times(1)).salvar(projeto);
    }

    @Test
    public void salvar_projeto_sem_gerente(){
        Projeto projeto = getProjeto(StatusProjeto.EM_ANALISE);

        Mockito.when(projetoService.getProjeto(1L)).thenReturn(projeto);

        projetoController.salvarProjeto(projeto, model, bindingResult);

        Mockito.verify(projetoService, Mockito.never()).salvar(projeto);
    }

    @Test
    public void remover_projeto(){
        Projeto projeto = getProjeto(StatusProjeto.EM_ANALISE);

        Mockito.when(projetoService.getProjeto(1L)).thenReturn(projeto);

        projetoController.removerProjeto(1L);

        Mockito.verify(projetoService, Mockito.times(1)).remover(projeto);
    }

    @Test
    public void remover_projeto_status_nao_excluir(){
        Projeto projeto = getProjeto(StatusProjeto.ENCERRADO);

        Mockito.when(projetoService.getProjeto(1L)).thenReturn(projeto);

        projetoController.removerProjeto(1L);

        Mockito.verify(projetoService, Mockito.never()).remover(projeto);
    }

    private Projeto getProjeto(StatusProjeto status){
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setNome("Nome Teste 1");
        projeto.setStatus(status);
        return  projeto;
    }
}
