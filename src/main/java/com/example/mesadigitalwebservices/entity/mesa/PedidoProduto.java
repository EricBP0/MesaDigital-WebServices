package com.example.mesadigitalwebservices.entity.mesa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "tb_pedido_produto")
public class PedidoProduto {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @JoinColumn(name = "id_pedido")
    @ManyToOne
    private Pedido pedido;

    @JoinColumn(name = "id_produto")
    @ManyToOne
    private Produto produto;



}
