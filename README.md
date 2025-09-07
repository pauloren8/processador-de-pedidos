# Processador de Pedidos
Um sistema que recebe pedidos de compra (simulação de e-commerce), processa em pipelines assíncronos e gera insights armazenados em bancos SQL e NoSQL.

Contexto do Sistema

1. Entrada de pedidos (API REST)
   O cliente cadastra um pedido (produto, valor, cliente, endereço, etc).
   O pedido é armazenado inicialmente em um banco SQL (ex.: PostgreSQL) para consistência transacional.
2. Processamento assíncrono (Kafka)
   Ao registrar o pedido, uma mensagem é publicada em um tópico do Kafka.
   Um consumer processa o pedido e enriquece os dados (ex.: cálculo de cashback, antifraude, geolocalização, etc).
3. Armazenamento de insights (NoSQL)
   Após processamento, os dados enriquecidos vão para um banco NoSQL (ex.: MongoDB ou DynamoDB) para consultas rápidas.
4. Consultas e relatórios
   API expõe endpoints para buscar pedidos e insights.
   Consultas complexas podem ser feitas diretamente no MongoDB (ex.: pedidos por região, valor médio por cliente).


Tecnologias no Projeto

1. Java 21 + Spring Boot 3 → base do backend.
2. Arquitetura Limpa → camadas:
   domain (entidades, casos de uso)
   application (serviços, lógica de negócio)
   infrastructure (Kafka, DB, AWS)
   adapters (controllers REST, DTOs, producers/consumers Kafka)
3. Mensageria: Kafka (producer/consumer).
4. Banco SQL: PostgreSQL (pedidos).
5. Banco NoSQL: MongoDB ou DynamoDB (insights).
6. Test Containers: subir PostgreSQL, MongoDB e Kafka em ambiente de teste.
7. Testes:
   Unitários → JUnit + Mockito.
   Integração → TestContainers simulando ambiente real.
8. AWS:
   RDS (PostgreSQL) ou Aurora.
   DynamoDB (NoSQL).
   MSK (Kafka gerenciado) ou Kafka no ECS/EKS.
   Deploy com ECS ou EKS.

Funcionalidades Mínimas
1. Criar pedido (POST /pedidos)
   Salva no PostgreSQL.
   Publica evento no Kafka.
2. Consumer de pedidos
   Lê do Kafka.
   Aplica lógica de negócio (ex.: cashback = 5%).
   Salva no MongoDB/DynamoDB.
3. Consultar pedido enriquecido (GET /pedidos/{id})
   Busca no MongoDB/DynamoDB.
4. Relatório de insights (GET /relatorios)
   Ex.: total de pedidos por cliente, média de valores, regiões com maior volume.


Futuramente usaremos: autenticação JWT, métricas (Micrometer/Prometheus), CI/CD, monitoramento (CloudWatch), etc.   
