package br.com.compass.mercado.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestMessageDto {
    private long id_pedido;
    private BigDecimal total;
}
