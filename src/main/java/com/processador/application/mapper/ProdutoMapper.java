package com.processador.application.mapper;

import com.processador.application.dto.CreateProdutoDTO;
import com.processador.application.dto.ProdutoDTO;
import com.processador.domain.model.Produto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProdutoMapper {

    public Produto toEntity(CreateProdutoDTO dto) {
        return Produto.builder()
                .id(UUID.randomUUID())
                .nome(dto.nome())
                .descricao(dto.descricao())
                .preco(dto.preco())
                .estoque(dto.estoque())
                .build();
    }

    public ProdutoDTO toDTO(Produto entity) {
        return new ProdutoDTO(
                entity.getId(),
                entity.getCodigo(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.getEstoque()
        );
    }
}
