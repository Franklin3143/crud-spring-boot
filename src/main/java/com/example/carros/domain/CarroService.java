package com.example.carros.domain;

import com.example.carros.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    public List<CarroDTO> getCarros() {

        //Essa lambda faz a mesma função que um for
        return rep.findAll().stream().map(CarroDTO::new).collect(Collectors.toList());

    }

    public Optional<CarroDTO> getCarroById(Long id) {

        return rep.findById(id).map(CarroDTO::new);

//        Optional<Carro> carro = rep.findById(id);
//        return carro.map(c -> Optional.of(new CarroDTO(c))).orElse(null);
    }

    public List<CarroDTO> getCarroByTipo(String tipo) {
        return rep.findAll().stream().map(CarroDTO::new).collect(Collectors.toList());
    }

    public Carro insert (Carro carro) {
        Assert.isNull(carro.getId(), "Não foi possível inserir o registro!");
        return rep.save(carro);
    }

    public Carro update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível inserir registro!");

        // Busca carro no banco de dados
        Optional<Carro> optional = rep.findById(id);
        if (optional.isPresent()) {
            Carro db = optional.get();
            // Copia as propriedades
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id" + db.getId());

            // Atualizar carro
            rep.save(db);

            return db;
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        Optional<CarroDTO> carro = getCarroById(id);
        if (carro.isPresent()) {
            rep.deleteById(id);
        } else {
            throw new RuntimeException("Não foi possível apagar veículo!");
        }
    }

//    public List<Carro> getCarrosFake() {
//        List<Carro> carros = new ArrayList<>();
//
//        carros.add(new Carro(1L, "Fusca"));
//
//        return carros;
//    }

}
