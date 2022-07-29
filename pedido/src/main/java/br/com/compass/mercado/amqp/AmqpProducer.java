package br.com.compass.mercado.amqp;

public interface AmqpProducer<T> {
    void producer(T t);
}
