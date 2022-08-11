package br.com.compass.site.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseItemDto {

    private long id;
    private String nome;
    private LocalDateTime dataValidade;
    private LocalDateTime dataCriacao;
    private BigDecimal valor;
    private String descricao;
    private int estoque;
    private String skuid;

}
