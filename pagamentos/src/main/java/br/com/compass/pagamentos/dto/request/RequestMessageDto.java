package br.com.compass.pagamentos.dto.request;

import lombok.Data;

@Data
public class RequestMessageDto {

    private long pedidoId;
    private String status;

}
