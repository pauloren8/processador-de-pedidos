package com.processador.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateProdutoDTO(
        @NotBlank(message = "O nome do produto é obrigatório")
        String nome,

        @NotBlank(message = "A descrição do produto é obrigatória")
        String descricao,

        @NotNull(message = "O preço do produto é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        Double preco,

        @NotNull(message = "O estoque do produto é obrigatório")
        @PositiveOrZero(message = "O estoque não pode ser negativo")
        Integer estoque
) {
}
