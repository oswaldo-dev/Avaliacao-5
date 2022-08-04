package br.com.compass.mercado.dto.request;

import br.com.compass.mercado.enums.Status;
import br.com.compass.mercado.enums.TipoPagamento;
import br.com.compass.mercado.model.Cartao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private TipoPagamento tipoPagamento;
    private RequestCartaoDto cartao;
    @Positive
    @NotNull
    private BigDecimal total;


}
