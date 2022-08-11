package br.com.compass.site.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResponseItemCheckoutDto {
    private String nome;
    private BigDecimal valor;
}
