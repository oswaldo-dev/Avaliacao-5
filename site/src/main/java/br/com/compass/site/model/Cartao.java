package br.com.compass.site.model;

import br.com.compass.site.enums.Marca;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "site_cartao")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String numero;
    @Column(length = 3)
    private String codigo;
    private int mesValidade;
    private int anoValidade;
    @Enumerated(EnumType.STRING)
    private Marca marca;
}
