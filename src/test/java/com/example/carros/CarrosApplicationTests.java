package com.example.carros;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.dto.CarroDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CarrosApplicationTests {

    @Autowired
    private CarroService service;

    @Test
    void test1() {

        Carro carro = new Carro();
        carro.setNome("Ferrari");
        carro.setTipo("esportivos");

       CarroDTO c = service.insert(carro);

       assertNotNull(c);

       Long id = c.getId();
       assertNotNull(id);

       // Busca o objeto
        Optional<CarroDTO> op = service.getCarroById(id);
        assertTrue(op.isPresent());

        c = op.get();
        assertEquals("Ferrari", c.getNome());
        assertEquals("esportivos", c.getTipo());

        // Deletar o objeto
        service.delete(id);

        //Verificar se deletou
        assertFalse(service.getCarroById(id).isPresent());


    }

}
