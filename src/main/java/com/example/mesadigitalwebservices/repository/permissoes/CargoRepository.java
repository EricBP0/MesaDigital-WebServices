package com.example.mesadigitalwebservices.repository.permissoes;

import com.example.mesadigitalwebservices.entity.permissoes.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
