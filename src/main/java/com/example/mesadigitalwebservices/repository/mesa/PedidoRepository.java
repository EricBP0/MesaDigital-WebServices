package com.example.mesadigitalwebservices.repository.mesa;

import com.example.mesadigitalwebservices.entity.mesa.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
