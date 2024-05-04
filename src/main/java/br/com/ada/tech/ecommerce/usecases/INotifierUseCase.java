package br.com.ada.tech.ecommerce.usecases;

public interface INotifierUseCase<T> {

    void registered(T object);

    void updated(T object);

    void deleted(T object);

}
