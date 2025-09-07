package com.processador.domain.model;

import com.processador.domain.enums.StatusPagamento;
import com.processador.domain.enums.TipoPagamento;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusPagamento statusPagamento;

    @Column(nullable = false)
    private Double valorTotal;

    // Relacionamento com itens do pedido
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens;

    // Relacionamento com cliente
    @ManyToOne
    @JoinColumn(name = "cliente", nullable = false)
    private Cliente cliente;

    // Endereço pode ser embutido (não precisa de tabela separada)
    @Embedded
    private EnderecoEntrega enderecoEntrega;

    // Se o pedido pode ter vários meios de pagamento, usamos coleção
    @ElementCollection(targetClass = TipoPagamento.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "pedido_tipos_pagamento", joinColumns = @JoinColumn(name = "pedido_id"))
    @Column(name = "tipo_pagamento")
    private List<TipoPagamento> tiposPagamento;

}
