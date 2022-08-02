package br.com.compass.pagamentos.dto;

import br.com.compass.mercado.dto.response.ResponseCartaoDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ResponseMenssageDto {

    private long id_pedido;
    private List<ResponseCartaoDto> cartaos;
    private BigDecimal total;

}
