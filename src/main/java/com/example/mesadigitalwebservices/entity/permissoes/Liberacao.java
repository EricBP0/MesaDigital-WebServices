package com.example.mesadigitalwebservices.entity.permissoes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "tb_liberacao")
public class Liberacao {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String nomeLiberacao;
}
