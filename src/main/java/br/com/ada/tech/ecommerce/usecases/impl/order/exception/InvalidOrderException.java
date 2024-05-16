package br.com.ada.tech.ecommerce.usecases.impl.order.exception;

public class InvalidOrderException extends RuntimeException {

    public InvalidOrderException(String message) {
        super(message);
    }

}
