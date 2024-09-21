package com.example.mesadigitalwebservices.repository.financeiro;

import com.example.mesadigitalwebservices.entity.financeiro.CustosFornecedores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustoFornecedorRepository extends JpaRepository<CustosFornecedores, Long> {
}
