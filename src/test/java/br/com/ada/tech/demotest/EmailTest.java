package br.com.ada.tech.demotest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({EmailServerExtension.class, MockitoExtension.class})
public class EmailTest {

    @Test
    public void teste_da_class_email() {
        System.out.println("Executando o teste.......");
    }

}
