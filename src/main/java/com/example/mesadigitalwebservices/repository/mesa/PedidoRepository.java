package com.example.mesadigitalwebservices.repository.mesa;

import com.example.mesadigitalwebservices.entity.mesa.Pedido;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findAllByComandaId(Long comandaId);
}
