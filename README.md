# Mili Project

Sistema de gerenciamento de produtos com suporte a produtos físicos e digitais.

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- MySQL
- Flyway (Migrations)
- Swagger/OpenAPI
- JUnit 5 e Mockito para testes

## 📋 Pré-requisitos

- JDK 17
- Maven
- MySQL

## 🔧 Configuração

1. Clone o repositório
2. Configure o banco de dados no arquivo `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mili
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

## ▶️ Executando o Projeto

```bash
# Compilar e executar
mvn spring-boot:run

# Executar apenas os testes
mvn test

# Gerar relatório de cobertura
mvn test jacoco:report
```

## 📝 Documentação da API

A documentação da API está disponível através do Swagger UI:
- http://localhost:8080/swagger-ui.html

Principais endpoints:
- `POST /produtos` - Adiciona novo produto
- `GET /produtos` - Lista todos os produtos
- `GET /produtos/{id}` - Busca produto por ID
- `PUT /produtos/{id}` - Atualiza um produto
- `DELETE /produtos/{id}` - Remove um produto
- `GET /produtos/media-precos` - Calcula média de preços

## 🏗️ Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── br/com/mili/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── repository/
│   │       └── service/
│   └── resources/
│       └── db/migration/
└── test/
    └── java/
        └── br/com/mili/
```

## 💡 Solução Implementada

- Utilização de herança para diferenciar produtos físicos e digitais
- Migrations com Flyway para versionamento do banco
- Testes unitários com cobertura
- Documentação automática com OpenAPI
- Arquitetura em camadas (Controller, Service, Repository)

## 🧪 Testes

Os testes cobrem as principais funcionalidades:
- Adição de produtos
- Atualização de produtos
- Cálculo de média de preços
- Validações de negócio

Para ver o relatório de cobertura, após executar os testes:
```bash
# O relatório estará disponível em:
target/site/jacoco/index.html
```
