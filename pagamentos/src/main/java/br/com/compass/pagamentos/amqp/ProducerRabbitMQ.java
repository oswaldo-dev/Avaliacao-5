package br.com.compass.pagamentos.amqp;

import br.com.compass.pagamentos.dto.request.RequestMessageDto;
import br.com.compass.pagamentos.interfaces.AmqpProducer;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProducerRabbitMQ implements AmqpProducer<RequestMessageDto> {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    private final String queue = "rk.producer.pagamento";
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
