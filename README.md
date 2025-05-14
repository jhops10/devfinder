# DevFinder API

Uma API RESTful desenvolvida com **Spring Boot** e **MongoDB** para gerenciar um diretório de desenvolvedores, permitindo o cadastro, listagem, filtragem por localização, habilidade, disponibilidade e mais.

---

## ⚙️ Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Data MongoDB
* MongoDB Atlas
* JUnit 5 + Mockito (testes unitários)

---

## 🚀 Funcionalidades

* ✅ Criar um novo desenvolvedor
* 🔍 Buscar todos os desenvolvedores
* 🔍 Filtrar por:

  * Localização
  * Habilidade
  * Disponibilidade
* ✏️ Atualizar dados de um desenvolvedor
* ❌ Deletar desenvolvedores
* 🧢 Testes unitários com cobertura de todas as funcionalidades

---

## 📦 Como rodar o projeto localmente

1. **Clone o repositório:**

```bash
git clone https://github.com/jhops10/devfinder.git
cd devfinder
```

2. **Configure o `application.properties` ou `application.yml` com sua conexão do MongoDB Atlas:**

```properties
spring.data.mongodb.uri=mongodb+srv://<usuario>:<senha>@<cluster>.mongodb.net/<database>?retryWrites=true&w=majority
```

3. **Execute o projeto com Maven:**

```bash
./mvnw spring-boot:run
```

---

## 📬 Exemplos de Endpoints

* `POST /api/developers` – Cadastrar novo dev
* `GET /api/developers` – Listar todos
* `GET /api/developers/available` – Filtrar disponíveis
* `PUT /api/developers/{id}` – Atualizar dados
* `DELETE /api/developers/{id}` – Deletar dev

---

## 🥪 Rodar os testes

```bash
./mvnw test
```

---
