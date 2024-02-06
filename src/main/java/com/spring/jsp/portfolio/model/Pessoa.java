package com.spring.jsp.portfolio.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    private String nome;
    @Column(name="datanascimento")
    private String dataNascimento;

    private String cpf;

    private boolean funcionario;
    private boolean gerente;
    @OneToMany
    private List<Projeto> projetos;
}
