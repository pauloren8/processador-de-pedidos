package com.processador.application.dto;

import java.util.UUID;

public record ClienteDTO(
        UUID id,
        String nome,
        String email,
        String cpfCnpj,
        String telefone) {
}
