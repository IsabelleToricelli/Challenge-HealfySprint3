# Healfy - Assistente de Bem-Estar, Nutrição e Rotina Saudável 

##  Sobre o Projeto
O **Healfy** é uma extensão do ecossistema de saúde digital CarePlus. Esta aplicação foi desenvolvida para auxiliar usuários com rotinas dinâmicas a melhorarem sua alimentação de forma prática e rápida, através da geração de planos alimentares personalizados.

A **API MealPlan** é o core engine deste projeto, responsável por gerenciar toda a inteligência e persistência dos planos de refeições.

---

## Tecnologias e Ferramentas
A API utiliza as tecnologias mais robustas e modernas do ecossistema Java para garantir escalabilidade e segurança:

* **Java 17+**: Utilização de recursos modernos como Records para DTOs.
* **Spring Boot 3**: Framework base para agilidade no desenvolvimento.
* **Spring Data JPA**: Abstração da camada de persistência.
* **Flyway**: Gerenciamento de migrações automáticas de banco de dados.
* **H2 Database**: Banco de dados relacional (em memória para desenvolvimento/testes).
* **Maven**: Automação e gerenciamento de dependências.
* **Postman**: Validação e testes de endpoints.

---


### Funcionalidades da API

A MealPlan permite realizar operações CRUD:

* Criar um plano de refeição
* Listar todos os planos por ID
* Atualizar um plano existente
* Excluir um plano por ID

Todas as operações retornam códigos HTTP adequados e mensagens de erro tratadas no controller.
---

## Arquitetura e Fluxo
A aplicação segue o padrão de camadas para separação de responsabilidades, facilitando a manutenção e a testabilidade.

### Diagrama de Sequência (Fluxo de Requisição)
Este diagrama ilustra como os dados trafegam desde a requisição do usuário até a persistência no banco de dados.

```mermaid
sequenceDiagram
    participant P as Postman / Client
    participant C as MealPlanController
    participant S as MealPlanService
    participant R as MealPlanRepository
    participant DB as H2 Database

    P->>C: POST/PUT /mealplans (JSON)
    C->>S: process(data)
    S->>S: Validações de Regra de Negócio
    S->>R: save(entity)
    R->>DB: SQL Insert/Update
    DB-->>R: Confirmação
    R-->>S: Objeto Persistido
    S-->>C: Data Transfer Object
    C-->>P: HTTP Status (201/200)

### Diagrama de classes

classDiagram
    class MealPlanModel {
        +Long id
        +String userName
        +String goal
        +Integer calories
        +LocalDate planDate
        +updateInformation(MealPlanRecord data)
    }
---


### URLs de Acesso
API Local: http://localhost:8085/mealplans

Console do Banco (H2): http://localhost:8085/h2-console

JDBC URL: jdbc:h2:file:~/testdb

Usuário: sa | Senha: senha

### Estrutura do JSON (Postman)

{ 
  "userName": "Isabelle", 
  "goal": "EMAGRECIMENTO", 
  "calories": 1800 
}

### Integrantes (Equipe FIAP)
Beatriz Rocha - RM: 552806

Isabelle Torricelli - RM: 552806

Luís Alberto - RM: 553507

Rafael Nascimento - RM: 553117
