package com.example.mesadigitalwebservices.entity.financeiro;

import com.example.mesadigitalwebservices.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity(name = "tb_financeiro")
public class Financeiro {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column
    private Date dataEmissao;

    @Column
    private BigDecimal valorGasto;

    @Column
    private BigDecimal valorFaturado;

    @JoinColumn(name = "emitido_por")
    @ManyToOne
    private User emitidoPor;



}
