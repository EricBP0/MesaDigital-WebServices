package com.example.mesadigitalwebservices.dto.cardapio;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoCreateDto {
    public Long id;
    public Long categoriaId;
    public Long estoqueId;
    public String nome;
    public String descricao;
    public Long quantidade;
    public BigDecimal valor;
    public List<Long> ingredientes;
}
