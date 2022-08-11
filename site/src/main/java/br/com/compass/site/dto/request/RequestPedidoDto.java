package br.com.compass.site.dto.request;

import br.com.compass.site.dto.response.ResponseItemDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class RequestPedidoDto {

    private String cpf;
    private List<ResponseItemDto> itens;
    private String tipoPagamento;
    private RequestCartaoDto cartao;
    private BigDecimal total;
}
