package br.com.compass.mercado.interfaces;

public interface AmqpConsumer<T> {
    void consumer(T t);
}
