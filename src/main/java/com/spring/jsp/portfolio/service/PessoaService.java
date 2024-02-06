package com.spring.jsp.portfolio.service;

import com.spring.jsp.portfolio.model.Pessoa;
import com.spring.jsp.portfolio.respository.PessoaRepository;
import com.spring.jsp.portfolio.respository.spec.PessoaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> getByCargo(boolean gerente, boolean funcionario){
        Specification<Pessoa> spec = PessoaSpecification.filtroPorCargo(gerente, funcionario);
        return pessoaRepository.findAll(spec);
    }

    public void salvar(Pessoa pessoa){
        pessoaRepository.save(pessoa);
    }
}
