package br.com.ada.tech.ecommerce.usecases.impl.order;

import br.com.ada.tech.ecommerce.model.Order;
import br.com.ada.tech.ecommerce.model.OrderStatus;
import br.com.ada.tech.ecommerce.usecases.order.IOrderShippingUseCase;
import br.com.ada.tech.ecommerce.usecases.order.IShippingNotifier;
import br.com.ada.tech.ecommerce.usecases.repository.IOrderRepository;
import jakarta.transaction.Transactional;

public class OrderShippingUseCaseImpl implements IOrderShippingUseCase {

    private IOrderRepository orderRepository;
    private IShippingNotifier notifierUseCase;

    public OrderShippingUseCaseImpl(
            IOrderRepository orderRepository,
            IShippingNotifier notifierUseCase
    ) {
        this.orderRepository = orderRepository;
        this.notifierUseCase = notifierUseCase;
    }

    @Override
    @Transactional
    public void shipping(Order order) {
        if (order.getStatus() != OrderStatus.PAID) {
            throw new RuntimeException("Pedido ainda não pago.");
        }
        order.setStatus(OrderStatus.FINISH);
        orderRepository.save(order);
        notifierUseCase.notify(order);
    }

}
