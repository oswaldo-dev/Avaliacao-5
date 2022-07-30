package br.com.compass.mercado.interfaces;

import br.com.compass.mercado.dto.request.RequestMessageDto;

public interface AmqpService {
    void sendToConsumer(RequestMessageDto messageDto);
}
