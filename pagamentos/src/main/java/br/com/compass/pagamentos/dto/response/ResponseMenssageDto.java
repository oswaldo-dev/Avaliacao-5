package br.com.compass.pagamentos.dto.response;

import br.com.compass.mercado.dto.response.ResponseCartaoDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResponseMenssageDto {

    private long idPedido;
    private String cpf;
    private ResponseCartaoDto cartao;
    private BigDecimal total;

}
