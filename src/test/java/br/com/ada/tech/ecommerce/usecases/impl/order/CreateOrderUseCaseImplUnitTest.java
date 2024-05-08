package br.com.ada.tech.ecommerce.usecases.impl.order;

import br.com.ada.tech.ecommerce.model.Customer;
import br.com.ada.tech.ecommerce.usecases.repository.ICustomerRepository;
import br.com.ada.tech.ecommerce.usecases.repository.IOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class CreateOrderUseCaseImplUnitTest {

    // Criar pedido para cliente existente. Deve ter sucesso

    // Criar pedido para cliente inexistente, deve ocorrer um erro.

    // Criar pedido e validar que o cliente esteja associado ao mesmo.

    // Validar que o pedido para cliente inexistente não seja persistido na base de dados

    // Criar pedido e validar que o status seja igual a OPEN

    // Criar pedido e validar que a lista de itens seja vazia

    // Criar pedido e validar que o repository tenha sido chamado(salvo na base de dados)

    // Criar pedido e validar que a data e hora estejam preenchida (orderedAt != null)

    private ICustomerRepository customerRepository;
    private IOrderRepository orderRepository;

    private CreateOrderUseCaseImpl useCase;

    @BeforeEach
    public void setup() {
        customerRepository = Mockito.mock(ICustomerRepository.class);
        orderRepository = Mockito.mock(IOrderRepository.class);

        useCase = new CreateOrderUseCaseImpl(orderRepository, customerRepository);

        Customer customer = new Customer();
        customer.setDocument("unit-test");
        Mockito.when(
            customerRepository.findByDocument(customer.getDocument())
        ).thenReturn(customer);
    }

}
