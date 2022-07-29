package br.com.compass.pagamentos.amqp.implementation;

import br.com.compass.pagamentos.amqp.AmqpConsumer;
import br.com.compass.pagamentos.dto.ResponseMenssageDto;
import br.com.compass.pagamentos.service.ConsumerService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer implements AmqpConsumer<ResponseMenssageDto> {

    @Autowired
    private ConsumerService consumerService;

    @Override
    @RabbitListener(queues = "${spring.rabbitmq.request.routing-key.producer}")
    public void consumer(ResponseMenssageDto responseMenssageDto) {
        try {
            consumerService.action(responseMenssageDto);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
