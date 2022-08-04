package br.com.compass.pagamentos.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestMessageDto {

    private long pedidoId;
    private String status;

}
