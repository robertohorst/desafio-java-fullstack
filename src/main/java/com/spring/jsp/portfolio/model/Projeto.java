package com.spring.jsp.portfolio.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "projeto")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    private String nome;

    @Column(name = "data_inicio")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inicio;

    @ManyToOne
    @JoinColumn(name = "idgerente")
    private Pessoa gerente;

    @Column(name = "data_previsao_fim")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date previsao;

    @Column(name = "data_fim")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fim;

    private Double orcamento;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusProjeto status;

    @Enumerated(EnumType.STRING)
    private StatusRisco risco;

    @ManyToMany
    @JoinTable(name = "membros",
            joinColumns = @JoinColumn(name = "idprojeto"),
            inverseJoinColumns = @JoinColumn(name = "idpessoa"))
    private List<Pessoa> membros;

}
