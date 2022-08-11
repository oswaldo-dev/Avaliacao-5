package br.com.compass.site.handler;

import br.com.compass.site.exception.CartaoNotFoundException;
import br.com.compass.site.exception.ClienteNotFoundException;
import br.com.compass.site.exception.EstoqueInsuficienteException;
import br.com.compass.site.exception.ItemNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultMessageResponse> handleValidationExceptions(MethodArgumentNotValidException e,
                                                                             HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errorMessage = new ArrayList<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        fieldErrors.forEach(ex -> {
            String msgError = "O campo '" + ex.getField() + "' " + ex.getDefaultMessage();
            errorMessage.add(msgError);
        });
        return ResponseEntity.status(status).body(new DefaultMessageResponse(String.valueOf(status.value()), errorMessage));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DefaultMessageResponse> handleHttpValidationExceptions(HttpMessageNotReadableException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorMessage = "Invalid information";

        if (e.getCause() instanceof InvalidFormatException) {
            InvalidFormatException cause = (InvalidFormatException) e.getCause();
            errorMessage = cause.getCause().getMessage();
        }

        return ResponseEntity.status(status).body(new DefaultMessageResponse(String.valueOf(status.value()), errorMessage));
    }

    @ExceptionHandler(value = ResponseStatusException.class)
    protected ResponseEntity<DefaultMessageResponse> handlerObjetoNotFound(ResponseStatusException exception) {
        if (exception.getStatus() ==  HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DefaultMessageResponse(String.valueOf(HttpStatus.NOT_FOUND.value())
                    , "Objeto nao encontrado."));

        } else if (exception.getStatus() == HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.OK).body(new DefaultMessageResponse(String.valueOf(HttpStatus.OK.value())
                    ,"Objeto ja existente."));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultMessageResponse(String.valueOf(HttpStatus.BAD_REQUEST.value())
                ,"Marca invalida."));

    }

    @ExceptionHandler(value = CartaoNotFoundException.class)
    protected ResponseEntity<DefaultMessageResponse> handlerCartaoNotFound(CartaoNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DefaultMessageResponse(String.valueOf(HttpStatus.NOT_FOUND.value())
                ,"Cartao nao encontrado."));
    }

    @ExceptionHandler(value = ClienteNotFoundException.class)
    protected ResponseEntity<DefaultMessageResponse> handlerClienteNotFound(ClienteNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DefaultMessageResponse(String.valueOf(HttpStatus.NOT_FOUND.value())
                ,"Cliente nao encontrado."));
    }

    @ExceptionHandler(value = EstoqueInsuficienteException.class)
    protected ResponseEntity<DefaultMessageResponse> handlerEstoqueBadRequest(EstoqueInsuficienteException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultMessageResponse(String.valueOf(HttpStatus.BAD_REQUEST.value())
                ,"Estoque insuficiente."));
    }

    @ExceptionHandler(value = ItemNotFoundException.class)
    protected ResponseEntity<DefaultMessageResponse> handlerItemNotFound(ItemNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DefaultMessageResponse(String.valueOf(HttpStatus.NOT_FOUND.value())
                ,"Item nao encontrado."));
    }
}
