package com.processador.application.controller;

import com.processador.application.dto.CreatePedidoDTO;
import com.processador.application.dto.PedidoDTO;
import com.processador.application.service.PedidoService;
import com.processador.domain.enums.StatusPagamento;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PedidoController {

    private final PedidoService pedidoService;

    /**
     * Cria um novo pedido
     */
    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody @Valid CreatePedidoDTO createPedidoDTO) {
//        log.info("Criando pedido para cliente: {}", createPedidoDTO.clienteId());
//        PedidoDTO pedidoSalvo = pedidoService.criarPedido(createPedidoDTO);
//        log.info("Pedido criado com sucesso: {}", pedidoSalvo.id());
//        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
        return  null;
    }

    /**
     * Busca um pedido pelo ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPedido(@PathVariable UUID id) {
//        log.info("Buscando pedido com ID: {}", id);
//        PedidoDTO pedido = pedidoService.buscarPedidoPorId(id);
//        log.info("Pedido encontrado: {}", pedido.id());
//        return ResponseEntity.ok(pedido);
        return null;
    }

    /** Listar todos os pedidos (com filtros opcionais) */
    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidosPorCliente(
            @RequestParam(required = false) UUID clienteId,
            @RequestParam(required = false) StatusPagamento status
    ) {
        log.info("Listando pedidos - clienteId: {}, status: {}", clienteId, status);
//        List<PedidoDTO> pedidos = pedidoService.listarPedidos(clienteId, status); // Implementar service
//        return ResponseEntity.ok(pedidos);
        return  null;
    }

    /** Cancelar ou deletar um pedido */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarPedido(@PathVariable UUID id) {
        log.info("Cancelando pedido {}", id);
//        pedidoService.cancelarPedido(id); // Implementar service
        return ResponseEntity.noContent().build();
    }
}
