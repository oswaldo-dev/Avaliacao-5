package br.com.compass.site.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
public class RequestCartaoDto {

    @CreditCardNumber
    @JsonProperty("numeroCartao")
    private String numero;
    @NotBlank
    @JsonProperty("nomeCartao")
    private String nome;
    @NotBlank
    @Length(max = 3, min = 3)
    @JsonProperty("codigoSeguranca")
    private String codigo;
    @Positive
    @Max(12)
    @JsonProperty("mesExpiracao")
    private int mesValidade;
    @Positive
    @JsonProperty("anoExpiracao")
    private int anoValidade;
    @NotNull
    private String marca;
}
