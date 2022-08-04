package br.com.compass.mercado.handler;

import br.com.compass.mercado.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    private static final String PEDIDO_NAO_ENCONTRADO = "Pedido nao encontrado.";
    private static final String ITEM_NAO_ENCONTRADO = "Item nao encontrado";
    private static final String DATA_INVALIDA = "Data invalida";
    private static final String OFERTA_FORA_DA_VALIDADE = "Oferta fora da validade";
    private static final String TOTAL_SO_ACEITA_VALORES_MAIORES_DO_QUE_ZERO = "total so aceita valores maiores do que zero";
    private static final String O_NOME_NAO_PODE_ESTAR_VAZIO = "O nome não pode estar vazio.";
    private static final String ESSE_PEDIDO_NÃO_PODE_SER_DELETADO_POR_ESTAR_COM_STATUS_DE_PAGAMENTO_APROVADO_REJEITADO =
            "Esse pedido não pode ser deletado por estar com status de pagamento aprovado/rejeitado";

    @ExceptionHandler(value = PedidoNotFoundException.class)
    protected ResponseEntity<MensagemErro> handlerPartidoNotFound(PedidoNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro(PEDIDO_NAO_ENCONTRADO));
    }

    @ExceptionHandler(value = ItemNotFoundException.class)
    protected ResponseEntity<MensagemErro> handlerAssociadoNotFound(ItemNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro(ITEM_NAO_ENCONTRADO));
    }

    @ExceptionHandler(value = DataInvalidException.class)
    protected ResponseEntity<MensagemErro> handlerAssociadoNotFound(DataInvalidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(DATA_INVALIDA));
    }

    @ExceptionHandler(value = ForaDaValidadeException.class)
    protected ResponseEntity<MensagemErro> handlerAssociadoNotFound(ForaDaValidadeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(OFERTA_FORA_DA_VALIDADE));
    }

    @ExceptionHandler(value = ValorDeDescontoInvalidoException.class)
    protected ResponseEntity<MensagemErro> handlerAssociadoNotFound(ValorDeDescontoInvalidoException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(TOTAL_SO_ACEITA_VALORES_MAIORES_DO_QUE_ZERO));
    }

    @ExceptionHandler(value = PedidoIndelibleException.class)
    protected ResponseEntity<MensagemErro> handlerAssociadoNotFound(PedidoIndelibleException exception) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new MensagemErro(
                ESSE_PEDIDO_NÃO_PODE_SER_DELETADO_POR_ESTAR_COM_STATUS_DE_PAGAMENTO_APROVADO_REJEITADO));
    }

    @ExceptionHandler(value = NomeVazioException.class)
    protected ResponseEntity<MensagemErro> handlerAssociadoNotFound(NomeVazioException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(O_NOME_NAO_PODE_ESTAR_VAZIO));
    }


    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError ->
                        "O campo: " + fieldError.getField() +
                                " " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(validationList));
    }
}
