package com.example.mesadigitalwebservices.entity.mesa;

import com.example.mesadigitalwebservices.entity.financeiro.Estoque;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter

@Entity(name = "tb_produto")
public class Produto {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private BigDecimal valor;

    @Column
    private String descricao;

    @Column
    private String nome;

    @Column
    private Long quantidade;

    @JoinColumn(name = "id_categoria")
    @ManyToOne
    private Categoria categoria;

    @JoinColumn(name = "id_estoque")
    @ManyToOne
    private Estoque estoque;

    @Column
    private Date dataCadastro;

    @Column
    private Date dataExclusao;
}
