package com.example.carros.domain;


import javax.persistence.*;

@Entity(name = "carro")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    O item abaixo associa o nome da coluna caso seja diferente da variável
//    @Column(name = "nome da coluna")
    private String Nome;

    public Carro() {
    }

    public Carro(Long id, String nome) {
        this.id = id;
        Nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
}
