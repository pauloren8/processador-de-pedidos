# 🛒 Processador de Pedidos  

[![Java](https://img.shields.io/badge/Java-21-red)]()  
[![Spring Boot](https://img.shields.io/badge/SpringBoot-3-brightgreen)]()  
[![Kafka](https://img.shields.io/badge/Kafka-Event--Driven-black)]()  
[![Tests](https://img.shields.io/badge/Tests-Unit%20%26%20Integration-blue)]()  
[![AWS](https://img.shields.io/badge/Deploy-AWS-orange)]()  

Um sistema de estudo que simula o fluxo de pedidos em um **e-commerce**, com processamento assíncrono e geração de insights em bancos **SQL** e **NoSQL**.  
Projeto criado para praticar **arquitetura limpa**, **mensageria com Kafka**, **testes com TestContainers** e **deploy na AWS**.  

---

## 📖 Sumário  

- [Contexto](#-contexto)  
- [Arquitetura](#-arquitetura)  
- [Tecnologias](#-tecnologias)  
- [Funcionalidades](#-funcionalidades)  
- [Roadmap](#-roadmap)  
- [Como Rodar Localmente](#-como-rodar-localmente)  


---

## 📌 Contexto  

1. **Entrada de pedidos (API REST)**  
   - Cadastro de pedido (produto, valor, cliente, endereço).  
   - Pedido armazenado em **PostgreSQL** para consistência transacional.  

2. **Processamento assíncrono (Kafka)**  
   - Publicação de evento em tópico Kafka.  
   - Consumer processa o pedido e enriquece os dados (ex.: cashback, antifraude, geolocalização).  

3. **Armazenamento de insights (NoSQL)**  
   - Dados enriquecidos persistidos em **MongoDB ou DynamoDB**.  

4. **Consultas e relatórios**  
   - API expõe endpoints para buscar pedidos e insights.  
   - Exemplos: pedidos por região, valor médio por cliente, total de pedidos.  

---

## 🏗️ Arquitetura  

+-----------------+        +-----------+        +----------------+
|   API REST      | -----> | PostgreSQL| -----> |    Kafka       |
| (Spring Boot)   |        |   (SQL)   |        | (Producer)     |
+-----------------+        +-----------+        +----------------+
                                                   |
                                                   v
                                            +--------------+
                                            | Kafka Consumer|
                                            |  (Spring Boot)|
                                            +--------------+
                                                   |
                                                   v
                                          +-----------------+
                                          | MongoDB/DynamoDB|
                                          |    (NoSQL)      |
                                          +-----------------+



---

## 🛠️ Tecnologias  

- **Java 21 + Spring Boot 3** → backend  
- **Arquitetura Limpa** →  
  - `domain` → entidades e casos de uso  
  - `application` → lógica de negócio  
  - `infrastructure` → Kafka, DB, AWS  
  - `adapters` → controllers, DTOs, producers/consumers  
- **Mensageria** → Kafka  
- **Banco SQL** → PostgreSQL  
- **Banco NoSQL** → MongoDB ou DynamoDB  
- **Testes** →  
  - JUnit + Mockito (unitários)  
  - TestContainers (integração com PostgreSQL, MongoDB e Kafka)  
- **AWS** →  
  - RDS (PostgreSQL) ou Aurora  
  - DynamoDB (NoSQL)  
  - MSK (Kafka gerenciado) ou Kafka no ECS/EKS  
  - Deploy com ECS ou EKS  

---

## ✅ Funcionalidades  

- [x] **Criar pedido** (`POST /pedidos`)  
- [x] **Publicar evento no Kafka**  
- [x] **Processar pedido via consumer** (ex.: aplicar cashback)  
- [x] **Persistir dados enriquecidos em NoSQL**  
- [x] **Consultar pedido enriquecido** (`GET /pedidos/{id}`)  
- [x] **Relatórios de insights** (`GET /relatorios`)  

---

## 🗺️ Roadmap  

- [x] Autenticação e autorização com JWT  
- [x] Métricas e monitoramento (Micrometer + Prometheus)  
- [x] CI/CD automatizado  
- [x] Observabilidade com AWS CloudWatch  
- [x] Deploy via Terraform (Infra as Code)  

---

## 🧪 Como Rodar Localmente  

```bash
# Clonar o repositório
git clone https://github.com/seu-usuario/processador-de-pedidos.git
cd processador-de-pedidos

# Subir dependências (Kafka, PostgreSQL, MongoDB)
docker-compose up -d

# Rodar a aplicação
./mvnw spring-boot:run

