package com.spring.jsp.portfolio.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jsp.portfolio.model.Pessoa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    PessoaController pessoaController;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void salvar() throws Exception {

        String body = objectMapper.writeValueAsString(getPessoa("João", true, false));

        mockMvc.perform(MockMvcRequestBuilders.post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void salvar_nao_permitido() throws Exception {

        String body = objectMapper.writeValueAsString(getPessoa(null, true, false));

        mockMvc.perform(MockMvcRequestBuilders.post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    private Pessoa getPessoa(String nome, boolean isGerente, boolean isFuncionario) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setGerente(isGerente);
        pessoa.setFuncionario(isFuncionario);

        return pessoa;
    }
}
