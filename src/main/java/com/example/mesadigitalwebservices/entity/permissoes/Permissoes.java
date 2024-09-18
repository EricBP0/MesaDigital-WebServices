package com.example.mesadigitalwebservices.entity.permissoes;

import com.example.mesadigitalwebservices.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

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
}
