<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gerenciador de portfólios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>
    <div class="p-2 right">
        <a class="btn btn-primary" role="button" href="/novo">Adicionar projeto</a>
        <!-- <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">Adicionar projeto</button> -->
    </div>
    <table class="table table-striped table-hover">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Nome</th>
                <th scope="col">Descrição</th>
                <th scope="col">Gerente</th>
                <th scope="col">Início</th>
                <th scope="col">Status</th>
                <th scope="col">Risco</th>
                <th class="text-center" scope="col" colspan="100%">&nbsp;</th>
            <tr>
        </thead>
        <c:forEach var="projeto" items="${projetos}">
            <tr id="${projeto.id}">
                <td>${projeto.id}</td>
                <td>${projeto.nome}</td>
                <td>${projeto.descricao}</td>
                <td>${projeto.gerente.nome}</td>
                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${projeto.inicio}" /></td>
                <td class="text-secondary">${projeto.status.descricao}</td>
                <td class="text-secondary">${projeto.risco.descricao}</td>
                <td>
                    <a href="/projetos/${projeto.id}" class="link-primary" title="Editar">
                        <span class="material-symbols-outlined">edit_square</span>
                    </a>
                </td>
                <td>
                    <a class="link-danger" href="#" data-bs-toggle="modal" data-bs-target="#modalExcluir" data-bs-id-projeto="${projeto.id}" title="Excluir">
                        <span class="material-symbols-outlined">delete</span>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>

   <!-- Modal excluir -->
   <div class="modal fade" id="modalExcluir" tabindex="-1" aria-labelledby="modalExcluir" aria-hidden="true">
     <div class="modal-dialog">
       <div class="modal-content">
         <div class="modal-header">
           <h1 class="modal-title fs-5" id="exampleModalLabel"></h1>
           <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
         </div>
         <div class="modal-body">
           Deseja realmente remover o projeto selecionado?
         </div>
         <div class="modal-footer">
           <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
           <button type="button" class="btn btn-primary">Remover</button>
         </div>
       </div>
     </div>
   </div>

    <!-- Modal erro -->
    <div class="modal fade" id="modalErro" tabindex="-1" aria-labelledby="modalErro" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">Operação inválida</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body"></div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
        </div>
      </div>
    </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

    <script>
        let modalExcluir = document.getElementById('modalExcluir');
        let modalErro = document.getElementById('modalErro');

        modalExcluir.addEventListener('show.bs.modal', function (event) {
          let idProjeto = event.relatedTarget.getAttribute('data-bs-id-projeto');
          let titulo = modalExcluir.querySelector('.modal-title');
          let btExcluir = modalExcluir.querySelector('.btn-primary');

          titulo.textContent = 'Remover projeto #' + idProjeto;
          btExcluir.addEventListener("click", () => excluirProjeto(idProjeto));
        });

       async function excluirProjeto(id){
            let modal = bootstrap.Modal.getInstance(modalExcluir);
            modal.hide();

            let url = '/projetos/'+id;
            let mensagemErro = await fetch(url, {
                  method: 'DELETE',
                })
                .then(response => {
                    if(response.status === 200){
                        return window.location.href='/projetos';
                    } else {
                        return response.text();
                    }
                })
                .then(message => {
                    return message;
                });

            if(mensagemErro){
                let mensagemModalErro = modalErro.querySelector('.modal-body');
                mensagemModalErro.textContent = mensagemErro;

                let modalErroInstance = new bootstrap.Modal(modalErro);
                modalErroInstance.show();
            }
        }
    </script>
</body>
</html>