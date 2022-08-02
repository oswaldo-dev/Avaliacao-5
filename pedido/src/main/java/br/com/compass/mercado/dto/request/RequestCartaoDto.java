package br.com.compass.mercado.dto.request;

import br.com.compass.mercado.enums.Marca;
import lombok.Data;

@Data
public class RequestCartaoDto {

    private String numeroCartao;
    private String nomeCartao;
    private String codigoSeguranca;
    private Marca marca;
    private int mesExpiracao;
    private int anoExpiracao;
    private String moeda;

}
