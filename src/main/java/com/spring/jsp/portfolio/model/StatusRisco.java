package com.spring.jsp.portfolio.model;

public enum StatusRisco {

    BAIXO_RISCO("Baixo"),
    MEDIO_RISCO("Médio"),
    ALTO_RISCO("Alto");

    private String descricao;

    StatusRisco(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
