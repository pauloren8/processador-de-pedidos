package com.processador.application.dto;

import com.processador.domain.enums.TipoPagamento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreatePedidoDTO(
        @NotNull(message = "ID do cliente é obrigatório")
        UUID clienteId,

        @NotEmpty(message = "A lista de itens não pode estar vazia")
        List<@Valid ItemPedidoDTO> itens,

        @NotNull(message = "O endereço de entrega é obrigatório")
        @Valid EnderecoEntregaDTO enderecoEntrega,

        @NotEmpty(message = "Ao menos um tipo de pagamento deve ser informado")
        List<TipoPagamento> tiposPagamento) {
}
