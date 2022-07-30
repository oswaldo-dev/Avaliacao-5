package br.com.compass.mercado.service;

import br.com.compass.mercado.interfaces.AmqpProducer;
import br.com.compass.mercado.dto.request.RequestMessageDto;
import br.com.compass.mercado.interfaces.AmqpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements AmqpService {

    @Autowired
    private AmqpProducer<RequestMessageDto> amqp;

    @Override
    public void sendToConsumer(RequestMessageDto messageDto) {
        amqp.producer(messageDto);
    }
}
