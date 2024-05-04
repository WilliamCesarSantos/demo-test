package br.com.ada.tech.ecommerce.usecases.order;

import br.com.ada.tech.ecommerce.model.Customer;
import br.com.ada.tech.ecommerce.model.Order;

public interface ICreateOrderUseCase {

    Order create(Customer customer);

}
