package com.example.mesadigitalwebservices.dto.Comanda;

import java.util.List;

public class RequestNewPedidoDto {
    public Long categoriaId;
    public Long comandaId;
    public String status;
    public String observacao;
    public List<ProdutoDto> produtos;
}
