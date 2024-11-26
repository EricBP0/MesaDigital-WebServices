package com.example.mesadigitalwebservices.entity.mesa;

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

}
