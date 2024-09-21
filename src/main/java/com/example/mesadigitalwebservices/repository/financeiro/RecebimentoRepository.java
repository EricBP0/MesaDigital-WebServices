package com.example.mesadigitalwebservices.repository.financeiro;

import com.example.mesadigitalwebservices.entity.financeiro.Recebimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecebimentoRepository extends JpaRepository<Recebimento, Long> {
}
