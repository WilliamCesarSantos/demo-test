package br.com.ada.tech.ecommerce.integration.controllers.order;

import br.com.ada.tech.ecommerce.model.Customer;
import br.com.ada.tech.ecommerce.model.Order;
import br.com.ada.tech.ecommerce.usecases.impl.order.exception.InvalidOrderException;
import br.com.ada.tech.ecommerce.usecases.order.ICreateOrderUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private ICreateOrderUseCase useCase;

    public OrderController(
            ICreateOrderUseCase useCase
    ) {
        this.useCase = useCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto create(@Valid @RequestBody OrderDto orderDto) {
        var customer = customerFromDto(orderDto);
        if (orderDto.getItems() != null && !orderDto.getItems().isEmpty()) {
            throw new InvalidOrderException("Pedido com items não é aceito");
        }
        var order = useCase.create(customer);
        return toDto(order);
    }

    private OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setOrderedAt(order.getOrderedAt());
        orderDto.setStatus(order.getStatus());
        orderDto.setShippingAddress(order.getShippingAddress());
        return orderDto;
    }

    private Customer customerFromDto(OrderDto dto) {
        var customer = new Customer();
        customer.setDocument(dto.getCustomerDocument());
        customer.setName(dto.getCustomerName());
        return customer;
    }

}
