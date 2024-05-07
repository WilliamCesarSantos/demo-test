package br.com.ada.tech.ecommerce.usecases.order;

import br.com.ada.tech.ecommerce.model.Order;

public interface IPaidOrderNotifier {

    public void notify(Order order);

}
