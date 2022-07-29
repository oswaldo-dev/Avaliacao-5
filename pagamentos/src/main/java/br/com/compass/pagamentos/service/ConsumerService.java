package br.com.compass.pagamentos.service;

import br.com.compass.pagamentos.dto.ResponseMenssageDto;

public interface ConsumerService {
    void action(ResponseMenssageDto message) throws Exception;
}
