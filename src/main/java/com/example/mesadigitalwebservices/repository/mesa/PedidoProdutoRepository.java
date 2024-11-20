package com.example.mesadigitalwebservices.repository.mesa;

import com.example.mesadigitalwebservices.entity.mesa.Pedido;
import com.example.mesadigitalwebservices.entity.mesa.PedidoProduto;
import com.example.mesadigitalwebservices.entity.mesa.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long> {
    List<PedidoProduto> findPedidoProdutoByPedido(Pedido pedido);
}
