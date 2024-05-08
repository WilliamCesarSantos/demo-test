package br.com.ada.tech.ecommerce.usecases.order;

import br.com.ada.tech.ecommerce.model.Order;

public interface IShippingNotifier {

    void notify(Order order);

}
