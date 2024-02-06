package com.spring.jsp.portfolio.respository.spec;

import com.spring.jsp.portfolio.model.Pessoa;
import org.springframework.data.jpa.domain.Specification;

public class PessoaSpecification {

    public static final String GERENTE = "gerente";
    public static final String FUNCIONARIO = "funcionario";

    private PessoaSpecification() {
        //empty
    }

    public static Specification<Pessoa> filtroPorCargo(boolean gerente, boolean funcionario){
        if(gerente) {
            return Specification.where(isGerente());
        }
        return Specification.where(isFuncionario());
    }

    private static Specification<Pessoa> isGerente(){
        return (((root, query, cb) -> cb.equal(root.get("gerente"), true)));
    }

    private static Specification<Pessoa> isFuncionario(){
        return (((root, query, cb) -> cb.equal(root.get("funcionario"), true)));
    }
}
