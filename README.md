# Library API

API REST simples para gerenciamento de livros e empréstimos, desenvolvida como projeto de aprendizado em Java com Spring Boot.

## Tecnologias usadas

- Java 17
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- Bean Validation (Jakarta Validation)
- MySQL Connector/J
- JUnit 5 + Mockito

## O que foi implementado

- CRUD de livros (`Book`)
- Registro de empréstimos de livros (`Loan`)
- Consulta de empréstimos por ID e por status
- Consulta de empréstimos atrasados
- Devolução de livro com atualização de estoque
- Camadas separadas: `controller`, `service`, `repository`, `model`, `dto`, `mapper`, `exception`
- Tratamento global de exceções com `@RestControllerAdvice`
- Dois testes unitários de serviço para `BookService` e `LoanService`

## Estrutura do projeto

- `controller/` — classes que expõem os endpoints REST
- `service/` — regras de negócio e orquestração dos casos de uso
- `repository/` — interfaces JPA para persistência
- `model/` — entidades JPA e `LoanStatus`
- `dto/` — objetos de transferência de dados para requisição e resposta
- `mapper/` — conversão entre DTOs e entidades
- `exception/` — exceções específicas e tratamento global

## Endpoints principais

### Livros

- `POST /books` — cria um novo livro
- `GET /books` — lista todos os livros
- `GET /books/{id}` — busca livro por ID
- `GET /books/isbn/{isbn}` — busca livro por ISBN
- `PUT /books/{id}` — atualiza um livro existente
- `DELETE /books/{id}` — remove um livro

### Empréstimos

- `POST /loans` — cria um novo empréstimo
- `GET /loans` — lista todos os empréstimos
- `GET /loans/{id}` — busca empréstimo por ID
- `GET /loans/status/{status}` — lista empréstimos por status
- `GET /loans/atrasados` — lista empréstimos atrasados
- `PUT /loans/{id}/devolver` — registra a devolução de um empréstimo

## Como executar

1. Configure o banco de dados MySQL em `src/main/resources/application.properties`.
2. Execute o comando:

```bash
./mvnw spring-boot:run
```

3. A API ficará disponível em `http://localhost:8080`.

## Testes

Foram criados testes unitários para as camadas de serviço:

- `src/test/java/com/bieldb/libraryapi/service/BookServiceTest.java`
- `src/test/java/com/bieldb/libraryapi/service/LoanServiceTest.java`

Execute os testes com:

```bash
./mvnw test
```

## Observações

- O projeto usa `BookCreateRequestDTO` para entrada de livro e `BookResponseDTO` para saída.
- A lógica de empréstimo decrementa o estoque do livro no momento da criação do empréstimo e incrementa ao devolver.
- O tratamento global de exceções converte erros de domínio em respostas HTTP apropriadas.
