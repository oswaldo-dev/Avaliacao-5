package br.com.compass.pagamentos.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RequestBancoDto {

    @JsonProperty("seller_id")
    private String sellerId;
    private RequestCustomerDto customer;
    @JsonProperty("payment_type")
    private String paymentType;
    private String currency;
    @JsonProperty("transaction_amount")
    private double transactionAmount;
    private RequestCardDto card;
}
