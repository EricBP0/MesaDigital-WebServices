package com.example.mesadigitalwebservices.repository.mesa;

import com.example.mesadigitalwebservices.entity.mesa.ProdutoIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoIngredienteRepository extends JpaRepository<ProdutoIngrediente, Long> {
    @Query(nativeQuery = true,value = "" +
            "SELECT * FROM tb_produto_ingrediente WHERE produto_id = :produtoId")
    Optional<List<ProdutoIngrediente>> findAllByProdutoId(Long produtoId);
    void deleteAllByProdutoId(Long produtoId);
}
