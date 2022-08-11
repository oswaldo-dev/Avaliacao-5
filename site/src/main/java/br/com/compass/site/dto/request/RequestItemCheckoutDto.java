package br.com.compass.site.dto.request;

import lombok.Data;

@Data
public class RequestItemCheckoutDto {
    private String skuId;
    private int quantidade;
}
