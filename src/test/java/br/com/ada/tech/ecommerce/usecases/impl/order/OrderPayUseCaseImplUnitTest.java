package br.com.ada.tech.ecommerce.usecases.impl.order;

import br.com.ada.tech.ecommerce.model.Order;
import br.com.ada.tech.ecommerce.model.OrderStatus;
import br.com.ada.tech.ecommerce.usecases.exception.InvalidOrderStatusException;
import br.com.ada.tech.ecommerce.usecases.order.IPaidOrderNotifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OrderPayUseCaseImplUnitTest {

    private IPaidOrderNotifier notifier;
    private OrderPayUseCaseImpl useCase;
    private Order order;

    @BeforeEach
    public void setup() {
        order = new Order();

        notifier = Mockito.mock(IPaidOrderNotifier.class);

        useCase = new OrderPayUseCaseImpl(notifier);
    }

    // tentar pagar pedido com status de aberto, deve ocorre erro
    @Test
    public void pay_orderWithStatusEqualsOpen_throwException() {
        order.setStatus(OrderStatus.OPEN);

        Assertions.assertThrows(InvalidOrderStatusException.class, () -> useCase.pay(order));
    }

    // Tentar pagar pedido com status de pago, deve ocorrer erro
    @Test
    public void pay_orderWithStatusEqualsPaid_throwException() {
        order.setStatus(OrderStatus.PAID);

        Assertions.assertThrows(InvalidOrderStatusException.class, () -> useCase.pay(order));
    }

    @Test
    public void pay_orderWithStatusEqualsOpen_mustNotNotifierCustomer() {
        order.setStatus(OrderStatus.OPEN);

        Assertions.assertThrows(InvalidOrderStatusException.class, () -> useCase.pay(order));

        Mockito.verify(notifier, Mockito.never()).notify(order);
    }

    // Tentar pagar pedido com status de pendente, deve ocorrer com sucesso.
    @Test
    public void pay_orderWithStatusEqualsPendingPayment_hasSuccess() {
        order.setStatus(OrderStatus.PENDING_PAYMENT);

        useCase.pay(order);
    }

    // Pagar um pedido com status de pendente, deve ficar com status de pago
    @Test
    public void pay_orderWithStatusEqualsPendingPayment_shouldChangeStatusToPaid() {
        order.setStatus(OrderStatus.PENDING_PAYMENT);

        useCase.pay(order);

        Assertions.assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    public void pay_orderWithStatusPending_shouldNotifierCustomer() {
        order.setStatus(OrderStatus.PENDING_PAYMENT);

        useCase.pay(order);

        Mockito.verify(notifier, Mockito.times(1)).notify(order);
    }

}
