package com.example.carros;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.dto.CarroDTO;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CarrosApplicationTests {

    @Autowired
    private CarroService service;

    @Test
    public void testSave() {

        Carro carro = new Carro();
        carro.setNome("Ferrari");
        carro.setTipo("esportivos");

       CarroDTO c = service.insert(carro);

       assertNotNull(c);

       Long id = c.getId();
       assertNotNull(id);

       // Busca o objeto
        c = service.getCarroById(id);
        assertNotNull(c);

        assertEquals("Ferrari", c.getNome());
        assertEquals("esportivos", c.getTipo());

        // Deletar o objeto
        service.delete(id);

        //Verificar se deletou
        try {
            assertNull(service.getCarroById(id));
            fail("O carro não foi excluído!!");
        } catch (ObjectNotFoundException e) {
            //OK
        }

    }

    @Test
    public void testLista() {
        List<CarroDTO> carros = service.getCarros();
        assertEquals(30, carros.size());
    }

    @Test
    public void testGet() {
        CarroDTO c = service.getCarroById(11L);

        assertNotNull(c);

        assertEquals("Ferrari FF", c.getNome());
    }


}
