package br.com.compass.mercado.dto.response;

import br.com.compass.mercado.enums.Status;
import br.com.compass.mercado.enums.StatusPagamento;
import br.com.compass.mercado.enums.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class ResponsePedidoDto {
    @JsonProperty("numeroDoPedido")
    private long id;
    private String cpf;
    private List<ResposeItemDto> itens;
    private Status status;
    private StatusPagamento statusPagamento;
    private TipoPagamento tipoPagamento;
    private ResponseCartaoDto cartao;
    private BigDecimal total;
}
