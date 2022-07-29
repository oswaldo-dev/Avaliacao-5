package br.com.compass.mercado.model;

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
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Item> itens;
    private BigDecimal total;

}
