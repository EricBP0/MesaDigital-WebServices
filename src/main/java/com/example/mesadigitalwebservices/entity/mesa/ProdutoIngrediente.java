package com.example.mesadigitalwebservices.entity.mesa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "tb_produto_ingrediente")
public class ProdutoIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private Produto produto;

    @Column
    private Long quantidade;

    @OneToOne
    private Ingrediente ingrediente;
}
