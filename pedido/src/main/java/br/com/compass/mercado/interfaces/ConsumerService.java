package br.com.compass.mercado.interfaces;

import br.com.compass.mercado.dto.response.ResponseMenssageDto;

public interface ConsumerService {
    void action(ResponseMenssageDto message) throws Exception;
}
