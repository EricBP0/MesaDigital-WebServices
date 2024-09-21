package com.example.mesadigitalwebservices.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

@Entity(name = "tb_users")
public class User {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column
    private String funcao;

    @Column
    private String documento;

    @Column
    private Date dataCriacao;

    @Column
    private Date dataRemocao;
}
