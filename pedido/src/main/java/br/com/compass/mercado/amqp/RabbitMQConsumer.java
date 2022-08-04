package br.com.compass.mercado.amqp;

import br.com.compass.mercado.dto.response.ResponseMenssageDto;
import br.com.compass.mercado.interfaces.AmqpConsumer;
import br.com.compass.mercado.interfaces.ConsumerService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer implements AmqpConsumer<ResponseMenssageDto> {

    @Autowired
    private ConsumerService consumerService;

    @Override
    @RabbitListener(queues = "rk.producer.pagamento")
    public void consumer(ResponseMenssageDto responseMenssageDto) {
        try {
            consumerService.action(responseMenssageDto);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
