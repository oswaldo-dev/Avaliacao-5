package br.com.compass.pagamentos.interfaces;

public interface AmqpProducer<T> {
    void producer(T t);
}
