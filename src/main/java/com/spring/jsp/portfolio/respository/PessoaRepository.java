package com.spring.jsp.portfolio.respository;

import com.spring.jsp.portfolio.model.Pessoa;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa> {

    List<Pessoa> findAll(@Nullable Specification<Pessoa> spec);

}
