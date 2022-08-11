package br.com.compass.site.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class RequestCheckoutDto {

    private List<RequestItemCheckoutDto> itens;
    private RequestClienteCheckoutDto cliente;

}
