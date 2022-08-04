package br.com.compass.mercado.model;

import br.com.compass.mercado.enums.Status;
import br.com.compass.mercado.enums.StatusPagamento;
import br.com.compass.mercado.enums.TipoPagamento;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cpf;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private List<Item> itens;
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.EM_ANDAMENTO;
    @Enumerated(value = EnumType.STRING)
    private StatusPagamento statusPagamento = StatusPagamento.PROCESSANDO;
    @Enumerated(value = EnumType.STRING)
    private TipoPagamento tipoPagamento;
    @OneToOne(cascade = CascadeType.ALL)
    private Cartao cartao;
    private BigDecimal total;

}
