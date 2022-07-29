package br.com.compass.mercado.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class ResponsePedidoDto {
    private long id;
    private String cpf;
    private List<ResposeItemDto> itens;
    private BigDecimal total;
}
