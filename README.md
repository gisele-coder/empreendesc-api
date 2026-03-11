# Empreendimentos SC API

API REST desenvolvida em **Kotlin + Spring Boot** para gerenciamento de empreendimentos.
O projeto demonstra boas práticas de desenvolvimento backend, incluindo arquitetura em camadas, validação de dados, paginação, tratamento global de erros e organização de código.

---

# Tecnologias Utilizadas

* Kotlin
* Spring Boot
* Spring Data JPA
* H2 Database
* Jakarta Validation
* Gradle
* REST API

---

# Arquitetura do Projeto

O projeto segue uma arquitetura em camadas para separar responsabilidades e facilitar manutenção.

```
controller
   ?
service
   ?
repository
   ?
database
```

Estrutura de pacotes:

```
br.com.empreendesc
 ??? controller
 ??? service
 ??? repository
 ??? domain
 ??? dto
 ??? mapper
 ??? exception
 ??? config
```

---

# Funcionalidades

* CRUD completo de empreendimentos
* Paginação de resultados
* Filtros por município e segmento
* Validação automática de dados
* Tratamento global de exceções
* Logs de operações
* Dados de exemplo carregados automaticamente

---

# Executando o Projeto

### Pré-requisitos

* Java 17+
* Gradle

### Rodar a aplicação

```
./gradlew bootRun
```

ou pelo IntelliJ executando a classe:

```
EmpreendescApiApplication
```

A API será iniciada em:

```
http://localhost:8080
```

---

# Endpoints da API

### Criar empreendimento

POST `/empreendimentos`

Exemplo de request:

```json
{
  "nome": "Tech Startup",
  "nomeEmpreendedor": "Maria Silva",
  "municipio": "Florianopolis",
  "segmento": "TECNOLOGIA",
  "contato": "contato@empresa.com",
  "status": "ATIVO"
}
```

---

### Listar empreendimentos

GET `/empreendimentos`

Suporte a paginação:

```
GET /empreendimentos?page=0&size=10
```

---

### Filtrar por município

```
GET /empreendimentos?municipio=Florianopolis
```

---

### Filtrar por segmento

```
GET /empreendimentos?segmento=TECNOLOGIA
```

---

### Buscar por ID

```
GET /empreendimentos/{id}
```

---

### Atualizar empreendimento

```
PUT /empreendimentos/{id}
```

---

### Remover empreendimento

```
DELETE /empreendimentos/{id}
```

---

# Tratamento de Erros

A API possui tratamento global de exceções retornando respostas padronizadas:

```json
{
  "timestamp": "2026-03-11T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Empreendimento não encontrado",
  "path": "/empreendimentos/99"
}
```

---

# Validação de Dados

Campos obrigatórios são validados automaticamente usando **Jakarta Validation**.

Exemplo de erro:

```json
{
  "status": 400,
  "error": "Validation Error",
  "message": "nome: não deve estar em branco"
}
```

---

# Banco de Dados

O projeto utiliza **H2 Database em memória** para facilitar execução e testes.

Dados de exemplo são carregados automaticamente na inicialização da aplicação.

---

# Objetivo do Projeto

Este projeto foi desenvolvido como **desafio técnico para avaliação de habilidades em desenvolvimento backend**, demonstrando:

* organização de código
* boas práticas de API REST
* uso de Spring Boot com Kotlin
* arquitetura limpa e extensível

---
