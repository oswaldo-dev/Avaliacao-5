package br.com.compass.pagamentos.interfaces;

public interface AmqpConsumer<T> {
    void consumer(T t);
}
