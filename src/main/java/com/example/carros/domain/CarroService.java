package com.example.carros.domain;

import com.example.carros.api.exception.ObjectNotFoundException;
import com.example.carros.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    public List<CarroDTO> getCarros() {

        //Essa lambda faz a mesma função que um for
        return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());

    }

    public CarroDTO getCarroById(Long id) {
        Optional<Carro> carro = rep.findById(id);
        return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));

//        Optional<Carro> carro = rep.findById(id);
//        return carro.map(c -> Optional.of(new CarroDTO(c))).orElse(null);
    }

    public List<CarroDTO> getCarroByTipo(String tipo) {
        return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO insert (Carro carro) {
        Assert.isNull(carro.getId(), "Não foi possível inserir o registro!");
        return CarroDTO.create(rep.save(carro));
    }

    public CarroDTO update(Carro carro, Long id) {
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

            return CarroDTO.create(db);
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
            rep.deleteById(id);
    }

}
