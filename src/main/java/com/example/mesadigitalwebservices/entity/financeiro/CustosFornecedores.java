package com.example.mesadigitalwebservices.entity.financeiro;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "tb_custos_fornecedores")
public class CustosFornecedores {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "id_custo")
    @ManyToOne
    private Custo custo;

    @JoinColumn(name = "id_fornecedor")
    @ManyToOne
    private Fornecedor fornecedor;
}
