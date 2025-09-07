package com.processador.application.service;

import com.processador.application.dto.CreateProdutoDTO;
import com.processador.application.dto.ProdutoDTO;
import com.processador.application.mapper.ProdutoMapper;
import com.processador.domain.model.Produto;
import com.processador.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoDTO criarProduto(CreateProdutoDTO dto) {
        Produto produto = produtoMapper.toEntity(dto);
        produto.setCodigo(gerarCodigoProduto());
        produtoRepository.save(produto);
        return produtoMapper.toDTO(produto);
    }

    public ProdutoDTO buscarPorId(UUID id) {
        log.info("Buscando produto com ID: {}", id);
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));
        return produtoMapper.toDTO(produto);
    }

    public List<ProdutoDTO> listarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        log.info(produtos.isEmpty()
                ? "Nenhum produto encontrado"
                : "Total de produtos encontrados: {}", produtos.size());
        return produtos.stream()
                .map(produtoMapper::toDTO)
                .toList();
    }

    public void deletar(UUID id) {
        log.info("Deletando produto com ID: {}", id);
        if (!produtoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado com ID: " + id);
        }
        produtoRepository.deleteById(id);
        log.info("Produto {} deletado com sucesso", id);
    }

    private String gerarCodigoProduto() {
        // Busca último produto cadastrado pelo código
        Optional<Produto> ultimoProduto = produtoRepository.findTopByOrderByCodigoDesc();

        int proximoNumero = 1;

        if (ultimoProduto.isPresent()) {
            String codigoUltimo = ultimoProduto.get().getCodigo(); // PROD-00005
            String numeroStr = codigoUltimo.split("-")[1]; // "00005"
            proximoNumero = Integer.parseInt(numeroStr) + 1;
        }

        // Formata o código com prefixo e número sequencial de 5 dígitos
        return String.format("PROD-%05d", proximoNumero);
    }
}
