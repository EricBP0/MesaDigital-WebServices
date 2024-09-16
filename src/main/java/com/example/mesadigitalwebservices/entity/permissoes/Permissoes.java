package com.example.mesadigitalwebservices.entity.permissoes;

import com.example.mesadigitalwebservices.entity.User;
import jakarta.persistence.*;

@Entity(name = "tb_permissoes")
public class Permissoes {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(nullable = false, name = "id_liberacao_cargo")
    @ManyToOne
    private LiberacaoCargo liberacaoCargo;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private User user;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
