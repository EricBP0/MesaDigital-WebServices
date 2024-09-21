package com.example.mesadigitalwebservices.repository.mesa;

import com.example.mesadigitalwebservices.entity.mesa.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
