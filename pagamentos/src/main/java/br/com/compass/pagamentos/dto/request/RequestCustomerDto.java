package br.com.compass.pagamentos.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestCustomerDto {

    @JsonProperty("document_type")
    private String documentType;
    @JsonProperty("document_number")
    private String documentNumber;

}
