package br.com.compass.mercado.dto.response;

import br.com.compass.mercado.enums.Marca;
import lombok.Data;

@Data
public class ResponseCartaoDto {
    private long id;
    private String numeroCartao;
    private String nomeCartao;
    private int codigoSeguranca;
    private Marca marca;
    private int mesExpiracao;
    private int anoExpiracao;
    private String moeda;
}
