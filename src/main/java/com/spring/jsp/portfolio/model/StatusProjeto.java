package com.spring.jsp.portfolio.model;

public enum StatusProjeto {

    EM_ANALISE("Em análise"),
    ANALISE_REALIZADA("Análise realizada"),
    ANALISE_APROVADA("Análise aprovada"),
    INICADO("Iniciado"),
    PLANEJADO("Planejado"),
    EM_ANDAMENTO("Em andamento"),
    ENCERRADO("Encerrado"),
    CANCELADO("Cancelado");

    private String descricao;

    StatusProjeto(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static final StatusProjeto[] STATUS_NAO_EXCLUIR = new StatusProjeto[]{INICADO, EM_ANDAMENTO, ENCERRADO};

}
