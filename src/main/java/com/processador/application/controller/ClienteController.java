package com.processador.application.controller;

import com.processador.application.dto.ClienteDTO;
import com.processador.application.dto.CreateClienteDTO;
import com.processador.application.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ClienteController {
    private final ClienteService clienteService;

    /**
     * Criar cliente
     */
    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody @Valid CreateClienteDTO createClienteDTO) {
        log.info("Criando cliente: {}", createClienteDTO.nome());
        ClienteDTO cliente = clienteService.criarCliente(createClienteDTO);
        log.info("Cliente criado com ID: {}", cliente.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    /**
     * Buscar cliente por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarCliente(@PathVariable UUID id) {
        log.info("Buscando cliente com ID: {}", id);
        ClienteDTO cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    /**
     * Listar todos os clientes
     */
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        log.info("Listando todos os clientes");
        List<ClienteDTO> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable UUID id) {
        log.info("Deletando cliente com ID: {}", id);

        clienteService.deletarCliente(id);

        log.info("Cliente {} deletado com sucesso", id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }

}
