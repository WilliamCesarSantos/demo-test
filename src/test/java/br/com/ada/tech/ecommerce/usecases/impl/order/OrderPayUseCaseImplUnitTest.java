package br.com.ada.tech.ecommerce.usecases.impl.order;

import br.com.ada.tech.ecommerce.model.Order;
import br.com.ada.tech.ecommerce.model.OrderStatus;
import br.com.ada.tech.ecommerce.usecases.exception.InvalidOrderStatusException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderPayUseCaseImplUnitTest {

    private OrderPayUseCaseImpl useCase;
    private Order order;

    @BeforeEach
    public void setup() {
        useCase = new OrderPayUseCaseImpl();

        order = new Order();
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

}
