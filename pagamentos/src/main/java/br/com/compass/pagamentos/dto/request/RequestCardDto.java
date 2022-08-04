package br.com.compass.pagamentos.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestCardDto {

    @JsonProperty("number_token")
    private String numberToken;
    @JsonProperty("cardholder_name")
    private String cardholderName;
    @JsonProperty("security_code")
    private String securityCode;
    private String brand;
    @JsonProperty("expiration_month")
    private String expirationMonth;
    @JsonProperty("expiration_year")
    private String expirationYear;

}