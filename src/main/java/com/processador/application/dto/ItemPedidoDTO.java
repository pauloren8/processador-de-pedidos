package com.processador.application.dto;

import java.util.UUID;

public record ItemPedidoDTO(
        UUID id,
        String produtoId,
        String nomeProduto,
        Integer quantidade,
        Double precoUnitario,
        Double subtotal) {
}
