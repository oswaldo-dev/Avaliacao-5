package br.com.compass.pagamentos.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestAuthDto {
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("api_key")
    private String apiKey;
}
