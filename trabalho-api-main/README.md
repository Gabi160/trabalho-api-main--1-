# trabalho-api

# 🐾 Clínica Veterinária(trabalho-api)

## 📋 Descrição

Esta é uma **API REST** em **Java com Spring Boot**, criada como projeto acadêmico para o curso de **Análise e Desenvolvimento de Sistemas**.  
O sistema permite gerenciar **animais**, suas informações, consultas e operações básicas de CRUD dentro de uma clínica veterinária.

---

## 🧩 Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Maven
- Jakarta Persistence (JPA/Hibernate)
- MySQL
- Swagger (documentação)
- JUnit / Jacoco (testes)

---

## ⚙️ Pré-requisitos

- JDK 17
- Maven
- Banco de dados MySQL em execução

---

## 🚀 Instalação e Execução

1. **Clone o repositório:**
```bash
git clone https://github.com/Gabi160/trabalho-api-main--1-.git

 **Navegue até o diretório do projeto:**

   ```bash
   cd trabalho-api-main--1-
   ```

4. **Compile e execute o projeto:**

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   A API estará disponível em `http://localhost:8080`.

## Documentação da API (Swagger)

A documentação da API pode ser acessada por meio do Swagger. Após iniciar o backend, você pode acessar a documentação por meio da seguinte URL:

[/swagger-ui/index.html](http://localhost:8080//swagger-ui/index.html)

## Endpoints

Abaixo está a descrição dos principais endpoints da API:

### **1. GET /Animal**

- **Descrição:** Retorna a lista de todos os animais cadastrados.
- **Resposta:**
  - **200 OK**
    ```json
    [
      {
        "id": 1,
        "nome": "Rex",
        "especie": "Cachorro",
        "raca": "Labrador",
        "idade": 3,
        "peso": 20.5,
        "consultas": []
      },
      {
        "id": 2,
        "nome": "Mia",
        "especie": "Gato",
        "raca": "Siamês",
        "idade": 2,
        "peso": 4.5,
        "consultas": []
      }
    ]
    ```

### **2. POST /Animal**

- **Descrição:** Cria um novo animal.
- **Corpo da Requisição:**
    ```json
    {
      "nome": "Bolt",
      "especie": "Cachorro",
      "raca": "Husky",
      "idade": 1,
      "peso": 15.0
    }
    ```
- **Resposta:**
  - **201 Created**
    ```json
    {
      "id": 3,
      "nome": "Bolt",
      "especie": "Cachorro",
      "raca": "Husky",
      "idade": 1,
      "peso": 15.0,
      "consultas": []
    }
    ```

### **3. GET /Animal/{id}**

- **Descrição:** Retorna um animal específico pelo ID.
- **Parâmetros de Caminho:**
  - `id`: ID do animal.
- **Resposta:**
  - **200 OK**
    ```json
    {
      "id": 1,
      "nome": "Rex",
      "especie": "Cachorro",
      "raca": "Labrador",
      "idade": 3,
      "peso": 20.5,
      "consultas": []
    }
    ```
  - **404 Not Found**
    ```json
    {
      "message": "Animal não encontrado"
    }
    ```

### **4. PUT /Animal/{id}**

- **Descrição:** Atualiza os dados de um animal existente.
- **Corpo da Requisição:**
    ```json
    {
      "nome": "Rex Atualizado",
      "especie": "Cachorro",
      "raca": "Labrador",
      "idade": 4,
      "peso": 22.0
    }
    ```
- **Resposta:**
  - **200 OK**
    ```json
    {
      "id": 1,
      "nome": "Rex Atualizado",
      "especie": "Cachorro",
      "raca": "Labrador",
      "idade": 4,
      "peso": 22.0,
      "consultas": []
    }
    ```
  - **404 Not Found**
    ```json
    {
      "message": "Animal não encontrado"
    }
    ```

### **5. DELETE /Animal/{id}**

- **Descrição:** Remove um animal pelo ID.
- **Parâmetros de Caminho:**
  - `id`: ID do animal.
- **Resposta:**
  - **204 No Content**
  - **404 Not Found**
    ```json
    {
      "message": "Animal não encontrado"
    }
    ```

### **6. POST /Animal/{id}/consultas**

- **Descrição:** Agenda uma nova consulta para o animal.
- **Corpo da Requisição:**
    ```json
    {
      "dataHora": "2025-11-10T10:30:00",
      "veterinario": "Dr. Marcos"
    }
    ```
- **Resposta:**
  - **200 OK**
    ```json
    {
      "message": "Consulta com Dr. Marcos em 2025-11-10T10:30:00 agendada com sucesso!"
    }
    ```

### **7. GET /Animal/{id}/consultas**

- **Descrição:** Lista todas as consultas agendadas do animal.
- **Resposta:**
  - **200 OK**
    ```json
    [
      "Consulta com Dr. Marcos em 2025-11-10T10:30:00"
    ]
    ```
