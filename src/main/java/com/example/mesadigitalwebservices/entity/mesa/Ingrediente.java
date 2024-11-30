package com.example.mesadigitalwebservices.entity.mesa;

import com.example.mesadigitalwebservices.entity.financeiro.Estoque;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "tb_ingrediente")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private Long quantidade;

    @Column
    private String unidade;

    @JoinColumn(name = "id_estoque")
    @ManyToOne
    private Estoque estoque;

}
