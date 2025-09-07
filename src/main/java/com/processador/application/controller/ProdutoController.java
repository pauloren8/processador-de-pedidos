package com.processador.application.controller;

import com.processador.application.dto.CreateProdutoDTO;
import com.processador.application.dto.ProdutoDTO;
import com.processador.application.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
@Slf4j
public class ProdutoController {

    private final ProdutoService produtoService;

    /** Criar produto */
    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody @Valid CreateProdutoDTO dto) {
        log.info("Requisição para criar produto: {}", dto);
        ProdutoDTO produto = produtoService.criarProduto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    /** Buscar produto por ID */
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable UUID id) {
        log.info("Requisição para buscar produto por ID: {}", id);
        ProdutoDTO produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    /** Listar todos os produtos */
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos() {
        log.info("Requisição para listar todos os produtos");
        List<ProdutoDTO> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    /** Deletar produto */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        log.info("Requisição para deletar produto com ID: {}", id);
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
