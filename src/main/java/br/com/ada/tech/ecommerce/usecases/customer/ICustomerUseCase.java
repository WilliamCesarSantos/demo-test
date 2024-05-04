package br.com.ada.tech.ecommerce.usecases.customer;

import br.com.ada.tech.ecommerce.model.Customer;

import java.util.List;

public interface ICustomerUseCase {

    void create(Customer customer);

    List<Customer> list();

    Customer findByDocument(String document);

    List<Customer> findByName(String name);

    void update(Customer customer);

    Customer delete(Long id);

}
