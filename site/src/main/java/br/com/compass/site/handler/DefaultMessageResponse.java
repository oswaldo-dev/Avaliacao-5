package br.com.compass.site.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultMessageResponse {

    private String status;
    private String message;
    private List<String> messages;

    public DefaultMessageResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public DefaultMessageResponse(String status, List<String> messages) {
        this.status = status;
        this.messages = messages;
    }
}
