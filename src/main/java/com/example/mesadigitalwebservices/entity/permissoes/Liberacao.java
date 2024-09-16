package com.example.mesadigitalwebservices.entity.permissoes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "tb_liberacao")
public class Liberacao {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String nomeLiberacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeLiberacao() {
        return nomeLiberacao;
    }

    public void setNomeLiberacao(String nomeLiberacao) {
        this.nomeLiberacao = nomeLiberacao;
    }
}
