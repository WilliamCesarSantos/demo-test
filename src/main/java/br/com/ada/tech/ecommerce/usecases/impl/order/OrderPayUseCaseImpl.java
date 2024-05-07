package br.com.ada.tech.ecommerce.usecases.impl.order;

import br.com.ada.tech.ecommerce.model.Order;
import br.com.ada.tech.ecommerce.model.OrderStatus;
import br.com.ada.tech.ecommerce.usecases.exception.InvalidOrderStatusException;
import br.com.ada.tech.ecommerce.usecases.order.IOrderPayUseCase;
import br.com.ada.tech.ecommerce.usecases.order.IPaidOrderNotifier;
import org.springframework.stereotype.Service;

@Service
public class OrderPayUseCaseImpl implements IOrderPayUseCase {

    private IPaidOrderNotifier notifier;

    public OrderPayUseCaseImpl(IPaidOrderNotifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void pay(Order order) {
        if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
            throw new InvalidOrderStatusException("Pedido em estado invalido");
        }
        order.setStatus(OrderStatus.PAID);
        this.notifier.notify(order);
    }

}
