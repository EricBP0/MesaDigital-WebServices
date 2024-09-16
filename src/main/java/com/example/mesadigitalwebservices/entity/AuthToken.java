package com.example.mesadigitalwebservices.entity;

import jakarta.persistence.*;

import java.util.Date;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
