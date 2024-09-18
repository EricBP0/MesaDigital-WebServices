package com.example.mesadigitalwebservices.entity.financeiro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "tb_estoque")
public class Estoque {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String nome;
}
