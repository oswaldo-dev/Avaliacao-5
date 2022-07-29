package br.com.compass.mercado.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestPedidoDto {

    @CPF
    @NotBlank
    private String cpf;
    @Size(min = 1)
    private List<@Valid RequestItemDto> itens;
    @Positive
    private BigDecimal total;


}
