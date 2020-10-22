package com.example.carros.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarroRepository extends CrudRepository <Carro, Long> {
    Iterable<Carro> findByTipo(String tipo);
}
