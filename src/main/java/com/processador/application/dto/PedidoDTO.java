package com.processador.application.dto;

import com.processador.domain.enums.StatusPagamento;
import com.processador.domain.enums.TipoPagamento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PedidoDTO(
        UUID id,
        LocalDateTime dataCriacao,
        StatusPagamento statusPagamento,
        Double valorTotal,
        ClienteDTO cliente,
        EnderecoEntregaDTO enderecoEntrega,
        List<ItemPedidoDTO> itens,
        List<TipoPagamento> tiposPagamento) {
}
