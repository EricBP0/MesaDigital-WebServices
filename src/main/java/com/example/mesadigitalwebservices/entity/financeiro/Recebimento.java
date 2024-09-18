package com.example.mesadigitalwebservices.entity.financeiro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "tb_recebimentos")
public class Recebimento {
    @Id
    @GeneratedValue
    private Long id;


}
