package br.com.compass.mercado.interfaces;

public interface AmqpProducer<T> {
    void producer(T t);
}
