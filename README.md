# 🏦 Gerador de Conta - API REST com Quarkus

## 📘 Descrição

Projeto desenvolvido como parte do curso *Introdução ao Quarkus*. O objetivo é construir uma **API REST** para cadastro e manutenção de contas de Pessoa Física, utilizando o framework **Quarkus**. A aplicação implementa as operações básicas de um CRUD completo.

---
## 🚀 Sobre o Projeto

A aplicação permite que a partir dos dados básicos de um cliente, seja criada uma conta Pessoa Física, onde de acordo o CEP do cliente, sua conta será vinculada a agência de sua UF.

---
## 💾 Tecnologias Utilizadas

- **Quarkus** (Framework principal)
- **H2 Database** (Banco de dados em memória)
- **Swagger/OpenAPI** (Documentação da API)
- **Mockito** (Testes unitários)
- **Frontend** (Interface de usuário)
- **Consumo de API externa** para obter UF via CEP
---

## 🧩 Extensões Quarkus Utilizadas
- quarkus-hibernate-orm
- quarkus-jdbc-h2
- quarkus-smallrye-openapi
- quarkus-junit5

---
## ▶️ Como executar

1. Execute a aplicação em modo de desenvolvimento:
   ```bash
   ./mvnw quarkus:dev

2. Acesse a aplicação via navegador:

☑️ Interface padrão: http://localhost:8080/

☑️ Interface Swagger: http://localhost:8080/q/swagger-ui/

---

## 🧾 Como gerar uma conta

1. Envie os dados do cliente no seguinte formato:
   ```
   {
     "nome": "string",
     "cpf": "string",
     "endereco": "string",
     "telefone": "string",
     "cep": "01001-000"
   }
   ⚠️ O CEP deve seguir o padrão: 00000-000
2. A conta será vinculada automaticamente à agência do estado correspondente ao CEP:
   ``` 
   "agencia": {
    "id": 25,
    "numeroAgencia": 25,
    "nomeAgencia": "SÃO PAULO",
    "ufAgencia": "SP"
   }
   🔹 Os dados das agências são carregados via import.sql ao iniciar a aplicação.
3. A conta gerada terá o seguinte formato:
   ```
   {
    "id": 1,
    "numeroConta": 1,
    "agencia": { ... },
    "cliente": { ... },
    "dataAbertura": "2025-08-28",
    "dataEncerramento": null,
    "ativa": true
   }
   🔸 A conta possui data de abertura, encerramento e status de atividade
---
## 🔗 Endpoints da API

| Método | Rota               | Descrição                                 |
|--------|--------------------|-------------------------------------------|
| GET    | `/contas`          | Retorna todas as contas cadastradas       |
| GET    | `/contas/{id}`     | Retorna uma conta por ID                  |
| POST   | `/contas`          | Cria uma nova conta                       |
| PUT    | `/contas/{id}`     | Atualiza registro do cliente              |
| PATCH  | `/contas/{id}`     | Encerra uma conta                         |
| DELETE | `/contas/{id}`     | Exclui uma conta                          |
---

🧪 Testes

✅ Como executar os testes

`./mvnw test `

🧪 Cobertura

• 	Testes unitários com Mockito

---
🧠 Sugestão de Funcionalidades que Podem Ser Acrescentadas

## 🛡️ Segurança e Autenticação
1. Autenticação JWT
- Autenticação baseada em tokens JWT.
- Criar perfis de acesso (ex: admin, operador).

## 🧪 Testes e Qualidade
1. Testes de Integração
- Simular chamadas reais à API e verificar persistência no banco.
2. Testes de Performance
- Avaliar tempo de resposta da API com JMeter

## 📦 Deploy e Escalabilidade
1. Containerização
- Criar um Dockerfile para empacotar a aplicação.
- Usar docker-compose para subir banco e API juntos.
2. Deploy em Nuvem
- Subir em serviços como Heroku, Railway, ou Azure.
- Configurar variáveis de ambiente e persistência.
---

## 👥 Desenvolvedores

- 👷 Genivaldo Ferreira da Silva
- 👷 Helsen Afonso Garcia Leme
- 👷 Jorge Michel Kim
- 👷 Matheus Ludovico Vasconcellos Costa
 ---
