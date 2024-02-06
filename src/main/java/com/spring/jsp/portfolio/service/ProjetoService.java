package com.spring.jsp.portfolio.service;

import com.spring.jsp.portfolio.model.Projeto;
import com.spring.jsp.portfolio.respository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<Projeto> getProjetos(){
        return projetoRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

    public Projeto getProjeto(Long idProjeto){
        return projetoRepository.getReferenceById(idProjeto);
    }

    public void salvar(Projeto projeto){
        projetoRepository.save(projeto);
    }

    public void remover(Projeto projeto){
        projetoRepository.delete(projeto);
    }
}
