package br.com.ada.tech.ecommerce.integration.controllers.order;

import br.com.ada.tech.ecommerce.model.Customer;
import br.com.ada.tech.ecommerce.model.Order;
import br.com.ada.tech.ecommerce.usecases.repository.ICustomerRepository;
import br.com.ada.tech.ecommerce.usecases.repository.IOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICustomerRepository customerRepository;
    @MockBean
    private IOrderRepository orderRepository;


    @BeforeEach
    public void setup() {
        Mockito.when(
                customerRepository.findByDocument("123456")
        ).thenReturn(new Customer());

        Mockito.doAnswer(invocation -> {
            var order = (Order) invocation.getArgument(0);
            order.setId(1l);
            return order;
        }).when(orderRepository).save(Mockito.any());
    }

    @Test
    public void create_orderWithoutCustomer_shouldThrowException() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/orders")
                        .content("""
                                {
                                    "items": [],
                                    "shippingAddress": "Minha casa"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.errors[0].customerDocument").value("must not be null")
        );
    }

    @Test
    public void create_orderWithItems_shouldThrowException() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/orders")
                        .content("""
                                {
                                    "customerName": "integration-test",
                                    "customerDocument": "123456",
                                    "items": [
                                        {
                                            "productId": 1
                                        }
                                    ],
                                    "shippingAddress": "Minha casa"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.message").value("Pedido com items não é aceito")
        );
    }

    @Test
    public void create_order_shouldHaveSuccess() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/orders")
                        .content("""
                                {
                                    "customerDocument":"123456",
                                    "items": [],
                                    "shippingAddress": "Minha casa"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").exists()
        );
    }

}
