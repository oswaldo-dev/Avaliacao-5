package br.com.compass.pagamentos.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseBancoDto {

    @JsonProperty("payment_id")
    private String paymentId;
    @JsonProperty("seller_id")
    private String sellerId;
    @JsonProperty("transaction_amount")
    private double transactionAmount;
    private String currency;
    private String status;
    @JsonProperty("receive_at")
    private LocalDateTime receivedAt;
    @JsonProperty("payment_type")
    private ResponseAuthorizationDto authorization;
}
