package br.com.compass.mercado.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ResposeItemDto {

    private long id;
    private String nome;
    private String dataDeCriacao;
    private String dataDeValidade;
    private BigDecimal valor;
    private String descricao;
    private List<ResponseOfertaDto> ofertas;

}
