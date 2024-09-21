package com.example.mesadigitalwebservices.repository.financeiro;

import com.example.mesadigitalwebservices.entity.financeiro.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
