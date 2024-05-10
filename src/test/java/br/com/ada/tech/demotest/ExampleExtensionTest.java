package br.com.ada.tech.demotest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(EmailServerExtension.class)
public class ExampleExtensionTest {

    @Test
    public void send_whenEmailHasContent_sendWithSuccess() {
        System.out.println("Executando o teste.......");
    }

    @Test
    public void testando() {
        System.out.println("Testando......");
    }

}
