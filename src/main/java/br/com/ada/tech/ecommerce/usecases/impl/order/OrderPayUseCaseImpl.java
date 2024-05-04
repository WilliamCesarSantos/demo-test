package br.com.ada.tech.ecommerce.usecases.impl.order;

import br.com.ada.tech.ecommerce.model.Order;
import br.com.ada.tech.ecommerce.model.OrderStatus;
import br.com.ada.tech.ecommerce.usecases.order.IOrderPayUseCase;
import org.springframework.stereotype.Service;

@Service
public class OrderPayUseCaseImpl implements IOrderPayUseCase {

    @Override
    public void pay(Order order) {
        if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
            throw new RuntimeException("Pedido em estado invalido");
        }
    }

}
