package br.com.ada.tech.ecommerce.usecases.order;

import br.com.ada.tech.ecommerce.model.Order;

public interface IShippingNotifierUseCase {

    void notify(Order order);

}
