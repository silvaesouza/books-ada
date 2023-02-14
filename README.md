# Enunciado - Trabalho ADA

## Entidades:

### entidade: editora

atributos:
```
id - Long
nome - String - Máx 255 caracteres
descricao - String - Não obrigatório
```

### entidade: categoria

atributos:
```
id - Long
nome - String - Máx 100 caracteres
```

### entidade: livro

atributos:
```
id - Long
editora_id - Long - relacionamento com a entidade editora
categoria_id - Long - relacionamento com a entidade categoria
nome - String
isbn - String - Máx 13 caracteres
```

**Com base nas entidades acima:**

- Crie o CRUD de cada entidade, possuindo os controllers, services, repositories, entities e DTOs.
- Crie um endpoint especifico para buscar os livros por categoria.
- Crie um endpoint especifico para buscar os livros por editora.
- Crie um endpoint que possa buscar o livro pelo nome ou pelo número isbn ou pelos dois, utilizando criteria ou query dsl.

_Bônus_

Crie a parte de autenticação de usuário e faça endpoints para salvar e buscar os livros favoritos do usuário logado.
