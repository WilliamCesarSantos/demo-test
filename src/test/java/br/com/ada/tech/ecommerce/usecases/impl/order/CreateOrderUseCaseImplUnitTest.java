package br.com.ada.tech.ecommerce.usecases.impl.order;

import br.com.ada.tech.ecommerce.model.Customer;
import br.com.ada.tech.ecommerce.model.Order;
import br.com.ada.tech.ecommerce.model.OrderStatus;
import br.com.ada.tech.ecommerce.usecases.repository.ICustomerRepository;
import br.com.ada.tech.ecommerce.usecases.repository.IOrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CreateOrderUseCaseImplUnitTest {

    private ICustomerRepository customerRepository;
    private IOrderRepository orderRepository;

    private CreateOrderUseCaseImpl useCase;

    private Customer customer;

    @BeforeEach
    public void setup() {
        customerRepository = Mockito.mock(ICustomerRepository.class);
        orderRepository = Mockito.mock(IOrderRepository.class);

        useCase = new CreateOrderUseCaseImpl(orderRepository, customerRepository);

        customer = new Customer();
        customer.setDocument("unit-test");
        Mockito.when(
                customerRepository.findByDocument(customer.getDocument())
        ).thenReturn(customer);
    }

    // Criar pedido para cliente existente. Deve ter sucesso
    @Test
    public void create_customerExistent_hasSuccess() {
        var order = useCase.create(customer);

        Assertions.assertNotNull(order);
    }

    // Criar pedido para cliente inexistente, deve ocorrer um erro.
    @Test
    public void create_customerNotExists_mustThrowsException() {
        var customer = new Customer();
        customer.setDocument("invalid");

        Assertions.assertThrows(
                IllegalStateException.class,
                () -> useCase.create(customer)
        );
    }

    // Criar pedido e validar que o cliente esteja associado ao mesmo.
    @Test
    public void create_customerExists_orderShouldHaveTheSameCustomer() {
        var order = useCase.create(customer);

        Assertions.assertEquals(customer, order.getCustomer());
    }

    // Validar que o pedido para cliente inexistente não seja persistido na base de dados
    @Test
    public void create_customerNotExists_mustNotSaveOrderInDatabase() {
        var customer = new Customer();
        customer.setDocument("invalid");

        Assertions.assertThrows(
                IllegalStateException.class,
                () -> useCase.create(customer)
        );

        Mockito.verify(orderRepository, Mockito.never()).save(Mockito.any());
    }

    // Criar pedido e validar que o status seja igual a OPEN
    @Test
    public void create_customerExists_orderShouldBeOpen(){
        var order = useCase.create(customer);

        Assertions.assertEquals(OrderStatus.OPEN, order.getStatus());
    }

    // Criar pedido e validar que a lista de itens seja vazia
    @Test
    public void create_order_itemsShouldBeEmpty() {
        var order = useCase.create(customer);

        Assertions.assertTrue(order.getItems() == null || order.getItems().isEmpty());
    }

    // Criar pedido e validar que o repository tenha sido chamado(salvo na base de dados)
    @Test
    public void create_order_mustBeSaved() {
        var order = useCase.create(customer);

        Mockito.verify(orderRepository, Mockito.times(1)).save(order);
    }

    // Criar pedido e validar que a data e hora estejam preenchida (orderedAt != null)
    @Test
    public void create_order_shouldHaveDataTime() {
        var order = useCase.create(customer);

        Assertions.assertNotNull(order.getOrderedAt());
    }

}
