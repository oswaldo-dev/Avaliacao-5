package br.com.compass.site.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RequestItemDto {

    @NotBlank
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @NotNull
    private LocalDateTime dataValidade;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @NotNull
    private LocalDateTime dataCriacao;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotBlank
    private String descricao;
    @Positive
    @NotNull
    private int estoque;

}
