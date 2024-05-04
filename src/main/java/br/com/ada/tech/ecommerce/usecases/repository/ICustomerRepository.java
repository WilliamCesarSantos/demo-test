package br.com.ada.tech.ecommerce.usecases.repository;

import br.com.ada.tech.ecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByDocument(String document);

    List<Customer> findByName(String name);

}
