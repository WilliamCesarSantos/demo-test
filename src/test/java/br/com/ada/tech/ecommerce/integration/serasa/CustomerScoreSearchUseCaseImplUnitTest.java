package br.com.ada.tech.ecommerce.integration.serasa;

import br.com.ada.tech.ecommerce.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CustomerScoreSearchUseCaseImplUnitTest {

    private SerasaFeignClient client;
    private CustomerScoreSearchUseCaseImpl useCase;

    @BeforeEach
    public void setup() {
        client = Mockito.mock(SerasaFeignClient.class);
        useCase = new CustomerScoreSearchUseCaseImpl(client);
    }

    @Test
    public void search_whenGetValue_shouldReturnIt() {
        var scoreDto = new ScoreResponseDto();
        scoreDto.setScore(50.0);
        scoreDto.setDocument("unit-test");
        scoreDto.setName("unit-test");
        Mockito.doReturn(scoreDto).when(client).search(Mockito.any());

        var customer = new Customer();
        customer.setDocument("unit-test");
        useCase.search(customer);

    }

}
