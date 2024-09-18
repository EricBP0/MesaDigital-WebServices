package com.example.mesadigitalwebservices.entity.financeiro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "tb_custos")
public class Custo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private BigDecimal valor;

    @Column
    private String descricao;

    @Column
    private Date dataCadastro;
}
