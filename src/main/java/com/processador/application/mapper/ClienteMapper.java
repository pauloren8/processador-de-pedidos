package com.processador.application.mapper;

import com.processador.application.dto.ClienteDTO;
import com.processador.application.dto.CreateClienteDTO;
import com.processador.domain.model.Cliente;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component // injetar o mapper no service
public class ClienteMapper {

    /** Converte CreateClienteDTO para entidade Cliente */
    public Cliente toEntity(CreateClienteDTO dto) {
        return Cliente.builder()
                .id(UUID.randomUUID()) // gerar UUID
                .nome(dto.nome())
                .email(dto.email())
                .cpfCnpj(dto.cpfCnpj())
                .telefone(dto.telefone())
                .build();
    }

    /** Converte entidade Cliente para ClienteDTO */
    public ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpfCnpj(),
                cliente.getTelefone()
        );
    }
}
