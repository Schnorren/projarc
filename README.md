# SARC - Sistema de Alocação de Recursos Computacionais

Bem-vindo ao **SARC**, um sistema desenvolvido para gerenciar a alocação de recursos computacionais em uma universidade. Este projeto foi desenvolvido como parte da disciplina de **Projeto e Arquitetura de Software**, com o objetivo de demonstrar um sistema backend que auxilia na gestão de recursos como salas de aula, equipamentos e outros recursos computacionais.

## Descrição do Projeto

O **SARC** é um sistema que permite o gerenciamento de reservas de recursos computacionais para aulas e turmas em uma universidade. Professores podem reservar salas de aula e recursos de acordo com o planejamento acadêmico, garantindo que não haja conflitos de horários ou alocações indevidas.

O sistema foi desenvolvido seguindo princípios de **Arquitetura Limpa (Clean Architecture)** e **Domain-Driven Design (DDD)**, proporcionando um código de fácil manutenção e escalável.

## Funcionalidades

- **Cadastro, edição e listagem de disciplinas**
- **Cadastro, edição e listagem de turmas**
- **Cadastro, edição e listagem de professores**
- **Cadastro, edição e listagem de recursos**
- **Cadastro, edição e listagem de reservas de recursos (alocações)**
- **Listagem de compromissos de uma turma (aulas)**
- **Listagem de reservas de recursos para uma determinada data**

## Arquitetura

O projeto está organizado em camadas, seguindo as melhores práticas de desenvolvimento:

- **Camada de Apresentação (Controllers)**: Gerencia as requisições HTTP e direciona para os serviços apropriados.
- **Camada de Aplicação (Services)**: Contém a lógica de negócio e regras do sistema.
- **Camada de Domínio (Models)**: Representa as entidades centrais do sistema.
- **Camada de Infraestrutura (Repositories)**: Interage com o banco de dados utilizando o Spring Data JPA.
- **Mappers**: Utiliza o MapStruct para converter entre entidades e DTOs.
- **Exceções Personalizadas**: Tratamento específico de erros através de exceções customizadas.
- **Tratamento Global de Exceções**: Centraliza o tratamento de exceções para respostas consistentes.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.4**
- **Spring Data JPA**
- **Spring Web**
- **H2 Database** (ambiente de desenvolvimento e testes)
- **MapStruct** (1.5.5.Final)
- **Lombok** (1.18.26)
- **SpringDoc OpenAPI** (2.1.0)
- **Maven** (gerenciamento de dependências e build)

## Pré-requisitos

- **Java 17** ou superior instalado
- **Maven** instalado
- IDE de sua preferência (recomendado: IntelliJ IDEA, Eclipse ou VS Code)

## Instalação e Execução

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/seu-usuario/sarc.git

2. **Navegue até o diretório do projeto:**
   ```bash
   cd sarc

3. **Compile o projeto e baixe as dependências:**
   ```bash
   mvn clean install

4. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run

5. **Acesse a aplicação:**
A aplicação estará disponível em: http://localhost:8080



## Endpoints Disponíveis

A seguir, uma visão geral dos endpoints disponíveis na API:

### Professores
- `GET /api/professores` - Lista todos os professores.
- `GET /api/professores/{id}` - Obtém um professor pelo ID.
- `POST /api/professores` - Cria um novo professor.
- `PUT /api/professores/{id}` - Atualiza um professor existente.
- `DELETE /api/professores/{id}` - Deleta um professor.

### Disciplinas
- `GET /api/disciplinas` - Lista todas as disciplinas.
- `GET /api/disciplinas/{codigo}` - Obtém uma disciplina pelo código.
- `POST /api/disciplinas` - Cria uma nova disciplina.
- `PUT /api/disciplinas/{codigo}` - Atualiza uma disciplina existente.
- `DELETE /api/disciplinas/{codigo}` - Deleta uma disciplina.

### Turmas
- `GET /api/turmas` - Lista todas as turmas.
- `GET /api/turmas/{codigo}` - Obtém uma turma pelo código.
- `POST /api/turmas` - Cria uma nova turma.
- `PUT /api/turmas/{codigo}` - Atualiza uma turma existente.
- `DELETE /api/turmas/{codigo}` - Deleta uma turma.
- `GET /api/turmas/disciplina/{disciplinaCodigo}` - Lista turmas por disciplina.

### Aulas
- `GET /api/aulas` - Lista todas as aulas.
- `GET /api/aulas/{id}` - Obtém uma aula pelo ID.
- `POST /api/aulas` - Cria uma nova aula.
- `PUT /api/aulas/{id}` - Atualiza uma aula existente.
- `DELETE /api/aulas/{id}` - Deleta uma aula.
- `GET /api/aulas/turma/{turmaCodigo}` - Lista aulas por turma.

### Recursos
- `GET /api/recursos` - Lista todos os recursos.
- `GET /api/recursos/{codigo}` - Obtém um recurso pelo código.
- `POST /api/recursos` - Cria um novo recurso.
- `PUT /api/recursos/{codigo}` - Atualiza um recurso existente.
- `DELETE /api/recursos/{codigo}` - Deleta um recurso.

### Alocações (Reservas)
- `GET /api/alocacoes` - Lista todas as alocações.
- `GET /api/alocacoes/{id}` - Obtém uma alocação pelo ID.
- `POST /api/alocacoes` - Cria uma nova alocação.
- `PUT /api/alocacoes/{id}` - Atualiza uma alocação existente.
- `DELETE /api/alocacoes/{id}` - Deleta uma alocação.
- `GET /api/alocacoes/data/{data}` - Lista alocações por data.
- `GET /api/alocacoes/turma/{turmaCodigo}` - Lista alocações por turma.


