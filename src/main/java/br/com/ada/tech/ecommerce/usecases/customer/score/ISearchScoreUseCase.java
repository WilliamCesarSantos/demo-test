package br.com.ada.tech.ecommerce.usecases.customer.score;

import br.com.ada.tech.ecommerce.model.Customer;
import br.com.ada.tech.ecommerce.model.CustomerScore;

import java.util.Optional;

public interface ISearchScoreUseCase {

    public Optional<CustomerScore> search(Customer customer);

}
