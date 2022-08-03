package br.com.compass.mercado.dto.request;

import br.com.compass.mercado.enums.Marca;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class RequestCartaoDto {

    @NotBlank
    private String numeroCartao;
    @NotBlank
    private String nomeCartao;
    @Positive
    @NotNull
    private int codigoSeguranca;
    private Marca marca;
    @Positive
    @Max(value = 12)
    @NotNull
    private int mesExpiracao;
    @Positive
    @NotNull
    private int anoExpiracao;
    @NotBlank
    private String moeda;

}
