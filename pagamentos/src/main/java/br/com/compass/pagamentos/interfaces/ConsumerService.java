package br.com.compass.pagamentos.interfaces;

import br.com.compass.pagamentos.dto.ResponseMenssageDto;

public interface ConsumerService {
    void action(ResponseMenssageDto message) throws Exception;
}
