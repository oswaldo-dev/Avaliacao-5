package br.com.compass.site.dto.response;

import lombok.Data;

@Data
public class ResponseCartaoDto {

    private long id;
    private String numero;
    private String nome;
    private String codigo;
    private int mesValidade;
    private int anoValidade;
    private String marca;

}
