package br.com.compass.site.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ResponseCheckoutDto {

    private long numeroDoPedido;
    private BigDecimal total;
    private String status;
    private List<ResponseItemCheckoutDto> itens;
}
