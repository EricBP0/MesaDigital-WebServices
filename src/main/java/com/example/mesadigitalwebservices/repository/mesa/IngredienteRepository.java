package com.example.mesadigitalwebservices.repository.mesa;

import com.example.mesadigitalwebservices.entity.mesa.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
    @Query(nativeQuery = true, value = "" +
            "SELECT * from tb_ingrediente where data_exclusao is null")
    List<Ingrediente> findAllWhereDataExclusaoIsNull();
    boolean existsByNome(String nome);
}
