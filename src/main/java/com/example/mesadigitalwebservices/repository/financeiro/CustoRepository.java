package com.example.mesadigitalwebservices.repository.financeiro;

import com.example.mesadigitalwebservices.entity.financeiro.Custo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustoRepository extends JpaRepository<Custo, Long> {
}
