package br.com.ada.tech.ecommerce.integration.email;

import br.com.ada.tech.ecommerce.model.Customer;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CustomerEmailNotifierImplUnitTest {

    @Mock
    private SendEmail sendEmail;

    @InjectMocks
    private CustomerEmailNotifierImpl notifier;

    private String from = "comunicado@ada.com.br";
    private List<String> to = List.of("unit-test");
    private Customer customer;

    @BeforeEach
    public void setup() {
        customer = new Customer();
        customer.setEmail(to);
    }

    @Test
    public void registered_whenCustomerHasEmail_shouldSendEmail() throws MessagingException {
        notifier.registered(customer);

        Mockito.verify(sendEmail, Mockito.times(1))
                .send(Mockito.eq(from), Mockito.eq(customer.getEmail()), Mockito.any(), Mockito.any());
    }

    @Test
    public void registered_whenCustomerNoHasEmail_mustNotSendEmail() throws MessagingException {
        customer.setEmail(null);

        notifier.registered(customer);

        Mockito.verify(sendEmail, Mockito.never())
                .send(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any());
    }

    @Test
    public void registered_whenEmailThrowsMessagingException_shouldRethrow() throws MessagingException {
        Mockito.doThrow(MessagingException.class)
                .when(sendEmail)
                .send(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any());

        Assertions.assertThrows(RuntimeException.class, () -> notifier.registered(customer));
    }

}
