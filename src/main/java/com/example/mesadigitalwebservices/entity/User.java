package com.example.mesadigitalwebservices.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity(name = "tb_users")
public class User {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String funcao;

    @Column
    private String documento;

    @Column
    private Date dataCriacao;

    @Column
    private Date dataRemocao;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataRemocao() {
        return dataRemocao;
    }

    public void setDataRemocao(Date dataRemocao) {
        this.dataRemocao = dataRemocao;
    }
}
