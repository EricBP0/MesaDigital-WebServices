package com.example.mesadigitalwebservices.repository.mesa;

import com.example.mesadigitalwebservices.entity.mesa.PedidoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long> {
}