package br.com.compass.mercado.dto.request;

import br.com.compass.mercado.dto.response.ResponseCartaoDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RequestMessageDto {
    private long idPedido;
    private String cpf;
    private ResponseCartaoDto cartao;
    private BigDecimal total;
}
