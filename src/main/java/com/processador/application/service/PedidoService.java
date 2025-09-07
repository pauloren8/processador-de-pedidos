package com.processador.application.service;

import org.springframework.stereotype.Service;

// Fluxo esperado ao criar pedido
//
//1. Cliente envia CreatePedidoDTO via API POST /pedidos.
//
//2. Backend cria o Pedido:
//
   //Calcula valorTotal somando subtotal dos itens.
//
   //Define statusPagamento = PENDENTE.
//
   //Define dataCriacao = LocalDateTime.now().
//
//3. Salva no PostgreSQL e publica evento no Kafka.
//
//4. Retorna PedidoDTO para o cliente.
@Service
public class PedidoService {
}
