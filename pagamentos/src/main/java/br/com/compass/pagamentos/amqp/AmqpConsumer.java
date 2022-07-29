package br.com.compass.pagamentos.amqp;

public interface AmqpConsumer<T> {
    void consumer(T t);
}
