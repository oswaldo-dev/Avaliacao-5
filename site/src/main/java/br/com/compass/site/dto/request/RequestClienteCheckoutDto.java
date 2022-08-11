package br.com.compass.site.dto.request;

import lombok.Data;

@Data
public class RequestClienteCheckoutDto {
    private String clienteId;
    private long cartaoId;
}
