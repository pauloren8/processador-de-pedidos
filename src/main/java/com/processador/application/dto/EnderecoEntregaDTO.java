package com.processador.application.dto;

public record EnderecoEntregaDTO(
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado) {
}
