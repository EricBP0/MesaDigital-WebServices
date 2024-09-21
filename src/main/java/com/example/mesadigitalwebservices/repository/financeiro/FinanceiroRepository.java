package com.example.mesadigitalwebservices.repository.financeiro;

import com.example.mesadigitalwebservices.entity.financeiro.Financeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceiroRepository extends JpaRepository<Financeiro, Long> {
}
