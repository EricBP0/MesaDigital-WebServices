package com.example.mesadigitalwebservices.entity.permissoes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

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
}
