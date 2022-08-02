package br.com.compass.mercado.dto.response;

import br.com.compass.mercado.enums.Status;
import br.com.compass.mercado.enums.StatusPagamento;
import br.com.compass.mercado.enums.TipoPagamento;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class ResponsePedidoDto {
    private long id;
    private String cpf;
    private List<ResposeItemDto> itens;
    private Status status;
    private StatusPagamento statusPagamento;
    private TipoPagamento tipoPagamento;
    private List<ResponseCartaoDto> cartoes;
    private BigDecimal total;
}
