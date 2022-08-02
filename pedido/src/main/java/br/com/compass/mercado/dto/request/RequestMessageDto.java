package br.com.compass.mercado.dto.request;

import br.com.compass.mercado.dto.response.ResponseCartaoDto;
import br.com.compass.mercado.model.Cartao;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RequestMessageDto {
    private long id_pedido;
    private List<ResponseCartaoDto> cartaos;
    private BigDecimal total;
}
