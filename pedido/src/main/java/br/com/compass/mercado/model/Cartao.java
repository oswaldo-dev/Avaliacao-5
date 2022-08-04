package br.com.compass.mercado.model;

import br.com.compass.mercado.enums.Marca;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Data
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String numeroCartao;
    private String nomeCartao;
    @Column(length = 3)
    private String codigoSeguranca;
    @Enumerated(value = EnumType.STRING)
    private Marca marca;
    private int mesExpiracao;
    private int anoExpiracao;
    private String moeda;

}
