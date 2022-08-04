package br.com.compass.pagamentos.interfaces;

import br.com.compass.pagamentos.dto.request.RequestMessageDto;

public interface AmqpService {
    void sendToConsumer(RequestMessageDto messageDto);
}
