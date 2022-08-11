package br.com.compass.site.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class RequestClienteDto {

    @CPF
    private String cpf;
    @NotBlank
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;
}
