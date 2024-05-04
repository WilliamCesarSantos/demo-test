package br.com.ada.tech.ecommerce.usecases.repository;

import br.com.ada.tech.ecommerce.model.Customer;
import br.com.ada.tech.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomer(Customer customer);

}
