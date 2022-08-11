package br.com.compass.mercado.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "pedido_itens")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataValidade;
    private BigDecimal valor;
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "itens_id")
    private List<Oferta> ofertas;

}
