package com.example.mesadigitalwebservices.entity.mesa;

import com.example.mesadigitalwebservices.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter

@Entity(name = "tb_comanda")
public class Comanda {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column(nullable = false)
    private Long numeroMesa;

    @Column
    private Date dataDeEntrada;

    @Column
    private Date dataDeSaida;

    @JoinColumn(name = "atendente")
    @ManyToOne
    private User atendente;

    @Column
    private BigInteger valor;


}
