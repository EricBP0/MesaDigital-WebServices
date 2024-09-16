package com.example.mesadigitalwebservices.entity.permissoes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "tb_cargo")
public class Cargo {

    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column
    private String nomeCargo;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }
}
