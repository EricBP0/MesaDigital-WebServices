package com.example.mesadigitalwebservices.entity.permissoes;

import jakarta.persistence.*;

@Entity(name = "tb_liberacao_cargo")
public class LiberacaoCargo {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(nullable = false, name = "id_cargo")
    @ManyToOne
    private Cargo cargo;

    @JoinColumn(nullable = false, name = "id_liberacao")
    @ManyToOne
    private Liberacao liberacao;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Liberacao getLiberacao() {
        return liberacao;
    }

    public void setLiberacao(Liberacao liberacao) {
        this.liberacao = liberacao;
    }
}
