package br.com.ada.tech.ecommerce.usecases.impl.customer;

import br.com.ada.tech.ecommerce.model.Customer;
import br.com.ada.tech.ecommerce.model.CustomerScore;
import br.com.ada.tech.ecommerce.usecases.INotifierUseCase;
import br.com.ada.tech.ecommerce.usecases.customer.score.ISearchScoreUseCase;
import br.com.ada.tech.ecommerce.usecases.repository.ICustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

public class CustomerUseCaseImplUnitTest {

    private ICustomerRepository customerRepository;
    private ISearchScoreUseCase searchUseCase;
    private INotifierUseCase<Customer> notifier;
    private CustomerUseCaseImpl useCase;

    @BeforeEach
    public void setup() {
        customerRepository = Mockito.mock(ICustomerRepository.class);
        searchUseCase = Mockito.mock(ISearchScoreUseCase.class);
        notifier = Mockito.mock(INotifierUseCase.class);

        useCase = new CustomerUseCaseImpl(customerRepository, searchUseCase, notifier);
    }

    @Test
    public void create_whenCustomerGetScore_shouldSaveCustomerWithScore() {
        Mockito.doReturn(
                Optional.of(new CustomerScore(80.0, "unit-test"))
        ).when(searchUseCase).search(Mockito.any());

        useCase.create(new Customer());

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        Mockito.verify(customerRepository, Mockito.times(1)).save(captor.capture());
        var saved = captor.getValue();
        Assertions.assertEquals(80.0, saved.getScore());
    }

}
