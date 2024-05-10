package br.com.ada.tech.ecommerce.usecases.impl.order;

import br.com.ada.tech.ecommerce.model.Customer;
import br.com.ada.tech.ecommerce.usecases.repository.ICustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ICustomerRepositoryComponentTest {

    @Autowired
    private ICustomerRepository repository;

    @BeforeEach
    public void setup() {
        Customer customer = new Customer();
        customer.setDocument("valid");
        customer.setId(1L);

        repository.save(customer);
    }

    @Test
    public void findByDocument_whenCustomerExists_returnIt() {
        var customer = repository.findByDocument("valid");

        Assertions.assertNotNull(customer);
    }

    @Test
    public void findByDocument_whenCustomerNotExists_returnNull() {
        var customer = repository.findByDocument("invalid");

        Assertions.assertNull(customer);
    }

}
