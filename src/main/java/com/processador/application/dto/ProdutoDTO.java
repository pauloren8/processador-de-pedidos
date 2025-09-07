package com.processador.application.dto;

import java.util.UUID;

public record ProdutoDTO(
        UUID id,
        String codigo,
        String nome,
        String descricao,
        Double preco,
        Integer estoque
) {
}
