package com.example.mesadigitalwebservices.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

@Entity(name = "tb_auth_token")
public class AuthToken {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Date dataExpiracao;

    @JoinColumn(nullable = false, name = "username")
    @OneToOne(fetch = FetchType.EAGER)
    private User userId;
}
