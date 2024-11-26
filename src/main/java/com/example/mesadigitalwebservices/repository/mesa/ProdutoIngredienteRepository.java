package com.example.mesadigitalwebservices.repository.mesa;

import com.example.mesadigitalwebservices.entity.mesa.ProdutoIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoIngredienteRepository extends JpaRepository<ProdutoIngrediente, Long> {
    Optional<List<ProdutoIngrediente>> findAllByProdutoId(Long produtoId);
}
