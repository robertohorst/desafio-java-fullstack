## Teste JAVA
___

Projeto foi desenvolvido usando a seguinte Stack

  - JAVA 17
  - Spring Boot 
  - Hibernate
  - JSP com Bootstrap
  - Postgres

Endpoint disponível para cadastrar pessoas novas:

```
  curl --location 'localhost:8080/pessoas' \
  --header 'Content-Type: application/json' \
  --data '{
  "nome": "Joca",
  "funcionario": "false",
  "gerente": "true"
  }
```

### OBSERVAÇÃO
Alteração na estrutura da tabela **membros** para que a chave primária fosse composta e referenciamento correto da chave estrangeira idpessoa <> tabela pessoa.
```
CREATE TABLE membros (
    idprojeto bigint NOT NULL,
    idpessoa bigint NOT NULL,
    CONSTRAINT pk_membros PRIMARY KEY (idprojeto, idpessoa),
    CONSTRAINT fk_membros_projeto FOREIGN KEY (idprojeto)
        REFERENCES projeto (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_membros_pessoa FOREIGN KEY (idpessoa)
        REFERENCES pessoa (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
``` 