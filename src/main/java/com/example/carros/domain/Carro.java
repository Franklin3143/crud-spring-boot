package com.example.carros.domain;


import lombok.*;

import javax.persistence.*;

@Entity(name = "carro")
@Data
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    O item abaixo associa o nome da coluna caso seja diferente da variável
//    @Column(name = "nome da coluna")
    private String Nome;
    private String tipo;



}
