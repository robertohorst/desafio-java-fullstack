<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gerenciador de portfólios - Novo projeto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

    <div class="p-4">
        <form:form action="/projetos/salvar" method="POST" modelAttribute="projeto">
            <form:input type="hidden" path="id"/>
            <div class="row">
                <div class="mb-3 col">
                    <label for="nome" class="form-label">Nome</label>
                    <form:input type="text" class="form-control" id="nome" path="nome" required="required" />
                </div>
                <div class="mb-3 col-sm-4">
                    <label for="status" class="form-label">Status</label>
                    <form:select name="status" class="form-select" path="status">
                        <c:forEach items="${listaStatus}" var="status">
                            <form:option value="${status}">${status.descricao}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="mb-3">
                <label for="descricao" class="form-label">Descrição</label>
                <form:input type="text" class="form-control" id="descricao" path="descricao" required="required" />
            </div>
            <div class="row">
                <div class="mb-3 col">
                    <label for="inicio" class="form-label">Início</label>
                    <form:input type="date" class="form-control" id="inicio" path="inicio" pattern="\d{1,2}/\d{1,2}/\d{4}" />
                </div>
                <div class="mb-3 col">
                    <label for="previsao" class="form-label">Previsão de término</label>
                    <form:input type="date" class="form-control" id="previsao" path="previsao" pattern="\d{1,2}/\d{1,2}/\d{4}" />
                </div>
                <div class="mb-3 col">
                    <label for="fim" class="form-label">Fim</label>
                    <form:input type="date" class="form-control" id="fim" path="fim" pattern="\d{1,2}/\d{1,2}/\d{4}" />
                </div>
            </div>
            <div class="mb-3">
                <label for="orcamento" class="form-label">Orçamento</label>
                <div class="input-group">
                    <span class="input-group-text">R$</span>
                    <form:input type="number" class="form-control" id="orcamento" path="orcamento" pattern="(\d{3})([\.])(\d{2})" />
                </div>
            </div>
            <div class="row">
                <div class="mb-3 col">
                    <label for="gerente" class="form-label">Gerente</label>
                    <form:select class="form-select" aria-label="Selecione um gerente" path="gerente" required="required">
                        <form:option value="0">Selecione ...</form:option>
                        <c:forEach items="${gerentes}" var="gerente">
                            <c:if test="${gerente.id != projeto.gerente.id}">
                                <form:option value="${gerente.id}">${gerente.nome}</form:option>
                            </c:if>
                            <c:if test="${gerente.id == projeto.gerente.id}">
                                <form:option value="${gerente.id}" selected="selected">${gerente.nome}</form:option>
                            </c:if>
                        </c:forEach>
                    </form:select>
                    <form:errors path="gerente" class="p-2 text-danger" />
                </div>
                <div class="mb-3 col-sm-4">
                    <label for="risco" class="form-label">Risco</label>
                    <form:select name="risco" class="form-select" path="risco">
                        <c:forEach items="${listaRiscos}" var="risco">
                            <form:option value="${risco}">${risco.descricao}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="mb-3">
                <label class="form-label">Membros</label>
                <c:forEach items="${funcionarios}" var="funcionario">
                    <div class="form-check">
                        <form:checkbox path="membros" class="form-check-input" id="${funcionario.id}" value="${funcionario}" />
                        <label class="form-check-label" for="${funcionario.id}">
                            ${funcionario.nome}
                        </label>
                    </div>
                </c:forEach>
            </div>
            <div class="form-check">

            </div>
            <button type="submit" class="btn btn-primary">Salvar</button>
            <button type="button" class="btn btn-secondary" onClick="voltar()">Voltar</button>
        </form:form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
    <script>
        function voltar() {
            window.history.back();
        }
    </script>
</body>