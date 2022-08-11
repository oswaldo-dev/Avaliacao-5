package br.com.compass.mercado.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "pedido_itens_ofertas")
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataValidade;
    private BigDecimal desconto;
    private String descricao;
}
