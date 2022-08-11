package br.com.compass.mercado.amqp;

import br.com.compass.mercado.dto.request.RequestMessageDto;
import br.com.compass.mercado.interfaces.AmqpProducer;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProducerRabbitMQ implements AmqpProducer<RequestMessageDto> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.request.routing-key.producer}")
    private String queue;
    @Value("${spring.rabbitmq.request.exchange.producer}")
    private String exchange;

    @Override
    public void producer(RequestMessageDto requestMessageDto) {
        try {
            rabbitTemplate.convertAndSend(exchange, queue, requestMessageDto);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
