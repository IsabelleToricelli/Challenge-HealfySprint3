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

## Como Executar o Projeto

**Pré-requisitos:**
* Java 17+ instalado.
* Maven instalado.
* 
  **Passo a passo:**
1. Clone o repositório:
```bash
   git clone [URL_DO_SEU_REPOSITORIO]
2. Acesse a pasta do projeto:
cd [Challenge-HealfySprint3]
3. Instale as dependências e compile o projeto:
```bash
mvn clean install
4. Inicie a aplicação:
```bash
mvn spring-boot:run

A API estará disponível localmente na porta 8085

---
## URLs de Acesso
* API Local: http://localhost:8085/mealplans
* Swagger UI (Documentação Automática): http://localhost:8085/swagger-ui.html
* OpenAPI (JSON): http://localhost:8085/v3/api-docs
* Console do Banco (H2): http://localhost:8085/h2-console
    * JDBC URL: jdbc:h2:file:~/testdb
    * Usuário: sa | Senha: senha

___

## Arquitetura e Fluxo
A aplicação segue o padrão de camadas para separação de responsabilidades, facilitando a manutenção e a testabilidade.

___

### Diagrama de Sequência (Fluxo de Requisição)
Este diagrama ilustra como os dados trafegam desde a requisição do usuário até a persistência no banco de dados.

sequenceDiagram
    participant P as Postman / Client
    participant C as MealPlanController
    participant S as MealPlanService
    participant R as MealPlanRepository
    participant DB as H2 Database

    P->>C: POST/PUT /mealplans (JSON)
    C->>S: process(data)
    S->>S: Validações
    S->>R: save(entity)
    R->>DB: SQL Insert/Update
    DB-->>R: Confirmação
    R-->>S: Objeto Persistido
    S-->>C: Data Transfer Object
    C-->>P: HTTP Status (201/200)

___

### Diagrama de classes



    class MealPlanModel {
        +Long id
        +String userName
        +String goal
        +Integer calories
        +LocalDate planDate
        +updateInformation(MealPlanRecord data)
    }

---

### Estrutura do JSON (Postman)

{ 
  "userName": "Isabelle", 
  "goal": "EMAGRECIMENTO", 
  "calories": 1800 
}
### Testes de Funcionalidade (CRUD e Persistência)

**1. Criação de Plano Alimentar (POST /mealplans)**
Validação do retorno 201 Created com o JSON processado corretamente:

<img width="477" height="142" alt="image" src="https://github.com/user-attachments/assets/d93aaead-a2a3-4529-b387-47297a80d1b9" />

**2. Persistência no Banco de Dados (H2 Console)**
Consulta realizada via SQL provando que o registro foi salvo com sucesso:

<img width="311" height="250" alt="image" src="https://github.com/user-attachments/assets/921fc7b9-89a7-4e06-af30-0193dbacdfe2" />

### Autenticação e Geração de Token (POST /auth/login)
Para acessar os endpoints protegidos da aplicação, é necessário primeiro realizar o login para obter o token JWT.

* **Payload de Exemplo:**
`{"username": "admin", "password": "123456"}`

Validação do retorno 200 OK com o token gerado com sucesso:

<img width="838" height="358" alt="image" src="https://github.com/user-attachments/assets/65234125-df84-4b32-80a3-0395bb71db24" />

## Testes Automatizados
O projeto possui testes de integração para validar a autenticação e o consumo dos serviços.

* **Status**: 100% de sucesso nos testes de integração.

<img width="663" height="227" alt="image" src="https://github.com/user-attachments/assets/32b738cb-0303-4a01-a60c-4d1fdcc69386" />

### Integrantes (Equipe FIAP)
Beatriz Rocha - RM: 552806

Isabelle Torricelli - RM: 552806

Luís Alberto - RM: 553507

Rafael Nascimento - RM: 553117
