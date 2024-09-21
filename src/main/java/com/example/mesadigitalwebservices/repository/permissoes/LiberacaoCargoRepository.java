package com.example.mesadigitalwebservices.repository.permissoes;

import com.example.mesadigitalwebservices.entity.permissoes.LiberacaoCargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiberacaoCargoRepository extends JpaRepository<LiberacaoCargo, Long> {
}
