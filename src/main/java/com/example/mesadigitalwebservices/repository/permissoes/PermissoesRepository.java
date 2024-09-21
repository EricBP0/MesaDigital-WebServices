package com.example.mesadigitalwebservices.repository.permissoes;

import com.example.mesadigitalwebservices.entity.permissoes.Permissoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissoesRepository extends JpaRepository<Permissoes, Long> {
}
