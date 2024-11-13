package com.example.mesadigitalwebservices.entity.mesa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private String tipo;

}
