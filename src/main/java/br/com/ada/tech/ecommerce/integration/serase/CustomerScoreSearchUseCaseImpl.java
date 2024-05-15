package br.com.ada.tech.ecommerce.integration.serase;

import br.com.ada.tech.ecommerce.model.Customer;
import br.com.ada.tech.ecommerce.model.CustomerScore;
import br.com.ada.tech.ecommerce.usecases.customer.score.ISearchScoreUseCase;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerScoreSearchUseCaseImpl implements ISearchScoreUseCase {

    private SerasaFeignClient serasaClient;

    public CustomerScoreSearchUseCaseImpl(SerasaFeignClient serasaClient) {
        this.serasaClient = serasaClient;
    }

    @Override
    public Optional<CustomerScore> search(Customer customer) {
        var dto = serasaClient.search(customer.getDocument());
        return Optional.of(
                new CustomerScore(dto.getScore(), "SERASA")
        );
    }

}
