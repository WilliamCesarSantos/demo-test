package br.com.ada.tech.ecommerce.integration.controllers.customer;

import br.com.ada.tech.ecommerce.integration.email.SendEmail;
import br.com.ada.tech.ecommerce.model.CustomerScore;
import br.com.ada.tech.ecommerce.usecases.customer.ICustomerUseCase;
import br.com.ada.tech.ecommerce.usecases.customer.score.ISearchScoreUseCase;
import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private ICustomerUseCase useCase;

    @SpyBean
    private ISearchScoreUseCase scoreUseCase;

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(
            ServerSetup.SMTP.port(3025)
    ).withConfiguration(GreenMailConfiguration.aConfig()
            .withUser("green@mail.com", "123456")
    ).withPerMethodLifecycle(false);

    @Test
    public void create_customerWithoutName_shouldThrowException() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/customers")
                        .content("""
                                {
                                    "document": "123456",
                                    "email": ["integration@test.com"],
                                    "telephone": ["123456"],
                                    "birthDate": "2024-01-01"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        );
//                .andExpect(
//                MockMvcResultMatchers.jsonPath("$.errors[1].name")
//                        .value("must not be null")
//        );

        Mockito.verify(useCase, Mockito.never()).create(Mockito.any());
    }

    @Test
    public void create_whenSearchScoreFails_shouldContinue() throws Exception {
        Mockito.doThrow(NullPointerException.class)
                .when(scoreUseCase).search(Mockito.any());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/customers")
                        .content("""
                                {
                                    "name": "integration-test",
                                    "document": "123456",
                                    "email": ["integration@test.com"],
                                    "telephone": ["123456"],
                                    "birthDate": "2024-01-01"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void create_whenSearchScoreOk_shouldContinue() throws Exception {
        Mockito.doReturn(
                Optional.of(new CustomerScore(100.0, "test"))
        ).when(scoreUseCase).search(Mockito.any());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/customers")
                        .content("""
                                {
                                    "name": "integration-test",
                                    "document": "456789",
                                    "email": ["integration@test.com"],
                                    "telephone": ["123456"],
                                    "birthDate": "2024-01-01"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void create_customerWithSuccess_shouldReturnId() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/customers")
                        .content("""
                                {
                                    "name": "integration-test",
                                    "document": "456789",
                                    "email": ["integration@test.com"],
                                    "telephone": ["123456"],
                                    "birthDate": "2024-01-01"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    @Order(1)
    public void create_customer_withSuccess() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/customers")
                        .content("""
                                {
                                    "name": "integration-test",
                                    "document": "123456",
                                    "email": ["integration@test.com"],
                                    "telephone": ["123456"],
                                    "birthDate": "2024-01-01"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(2)
    public void get_customerByName_returnsIt() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/customers?name=integration-test")
                                .accept(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(
                        MockMvcResultMatchers.status()
                                .isOk()
                );
    }

}
