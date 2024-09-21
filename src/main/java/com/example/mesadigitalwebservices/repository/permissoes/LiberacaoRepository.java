package com.example.mesadigitalwebservices.repository.permissoes;

import com.example.mesadigitalwebservices.entity.permissoes.Liberacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiberacaoRepository extends JpaRepository<Liberacao, Long> {
}
