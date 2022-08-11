package br.com.compass.pagamentos.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseCustomerDto {

    @JsonProperty("document_type")
    private String documentType;
    @JsonProperty("document_number")
    private String documentNumber;

}
