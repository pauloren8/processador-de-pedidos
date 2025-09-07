package com.processador.application.service;

import com.processador.application.dto.ClienteDTO;
import com.processador.application.dto.CreateClienteDTO;
import com.processador.application.mapper.ClienteMapper;
import com.processador.domain.model.Cliente;
import com.processador.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteService {

    private final ClienteMapper clienteMapper;
    private final ClienteRepository clienteRepository;

    /**
     * Cria um novo cliente.
     */
    public ClienteDTO criarCliente(CreateClienteDTO dto) {
        log.info("Iniciando criação de cliente: {}", dto.nome());

        Cliente cliente = clienteMapper.toEntity(dto);
        clienteRepository.save(cliente);

        log.info("Cliente criado com sucesso: {}", cliente.getId());
        return clienteMapper.toDTO(cliente);
    }

    /**
     * Busca cliente por ID.
     * Lança ResourceNotFoundException caso não exista.
     */
    public ClienteDTO buscarClientePorId(UUID id) {
        log.debug("Buscando cliente com ID: {}", id);

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Cliente não encontrado para ID: {}", id);
                    return new ResourceNotFoundException("Cliente não encontrado com ID: " + id);
                });

        log.info("Cliente encontrado: {}", cliente.getId());
        return clienteMapper.toDTO(cliente);
    }

    /**
     * Lista todos os clientes cadastrados.
     */
    public List<ClienteDTO> listarClientes() {
        log.debug("Listando todos os clientes");

        List<Cliente> clientes = clienteRepository.findAll();

        log.info(clientes.isEmpty()
                ? "Nenhum cliente encontrado no banco de dados"
                : "Total de clientes encontrados: {}", clientes.size());

        return clientes.stream()
                .map(clienteMapper::toDTO)
                .toList();
    }

    /**
     * Deleta um cliente pelo ID.
     * Caso não exista, lança ResourceNotFoundException.
     */
    public void deletarCliente(UUID id) {
        log.debug("Tentando deletar cliente com ID: {}", id);

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Tentativa de deletar cliente inexistente com ID: {}", id);
                    return new ResourceNotFoundException("Cliente não encontrado com ID: " + id);
                });

        clienteRepository.delete(cliente);
        log.info("Cliente deletado com sucesso: {}", id);
    }
}
