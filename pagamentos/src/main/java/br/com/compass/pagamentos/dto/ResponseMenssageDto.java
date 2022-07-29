package br.com.compass.pagamentos.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResponseMenssageDto {

    private long id_pedido;
    private BigDecimal total;

}
