package com.example.mesadigitalwebservices.repository.mesa;

import com.example.mesadigitalwebservices.entity.mesa.Categoria;
import com.example.mesadigitalwebservices.entity.mesa.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(nativeQuery = true, value = "" +
            "SELECT * from tb_produto where data_exclusao is null")
    List<Produto> findAllWhereDataExclusaoIsNotNull();

    boolean existsByNome(String nome);

    List<Produto> findAllByCategoriaTipo(String tipo);

    List<Produto> findAllByCategoria(Categoria categoria);
}
