package br.com.ada.tech.ecommerce.usecases.impl.order;

import br.com.ada.tech.ecommerce.model.Order;
import br.com.ada.tech.ecommerce.usecases.order.IPaidOrderNotifier;

public class PaidOrderNotifierImpl implements IPaidOrderNotifier {
    @Override
    public void notify(Order order) {
        System.out.println("Notificando todo mundo");
    }

}
