package br.com.ada.tech.ecommerce.usecases.order;

import br.com.ada.tech.ecommerce.model.Customer;
import br.com.ada.tech.ecommerce.model.Order;

public interface ICreateOrderUseCase {

    /*
     * 1 - Pedido deve ser criado para um cliente já existente na base
     * 2 - Pedido deve iniciar com status de OPEN
     * 3 - Pedido não deve possuir itens ao ser criado
     * 4 - Pedido deve ser persistido na base de dados
     */
    Order create(Customer customer);

}
