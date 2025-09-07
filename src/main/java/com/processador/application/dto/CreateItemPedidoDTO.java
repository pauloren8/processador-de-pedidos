package com.processador.application.dto;

public record CreateItemPedidoDTO(
        String produtoId,
        String nomeProduto,
        Integer quantidade,
        Double precoUnitario) {
}
