package com.example.mesadigitalwebservices.entity.mesa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

@Entity(name = "tb_pedido")
public class Pedido {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @JoinColumn(name = "id_comanda")
    @ManyToOne
    private Comanda comanda;

    @Column
    private Date dataCriacao;

}
