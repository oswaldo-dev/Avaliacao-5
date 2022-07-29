package br.com.compass.mercado.util;

import br.com.compass.mercado.exceptions.DataInvalidException;
import br.com.compass.mercado.exceptions.ForaDaValidadeException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

@Component
public class ValidaData {
    public LocalDateTime mudaParaISO(String data) {
        try {
            DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss").withResolverStyle(ResolverStyle.STRICT);
            return LocalDateTime.parse(data, formatoBR);

        } catch (Exception e) {
            throw new DataInvalidException();
        }
    }

    public String mudaParaBr (LocalDateTime data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss").withResolverStyle(ResolverStyle.STRICT);
            return data.format(formatter);
        } catch (Exception e) {
            throw new DataInvalidException();
        }
    }

    public void verificaData(String dataCriacao, String dataValidade) {
        LocalDateTime localDateTimeCriacao = mudaParaISO(dataCriacao);
        LocalDateTime localDateTimeValidade = mudaParaISO(dataValidade);

        if (localDateTimeCriacao.isAfter(localDateTimeValidade)) {
            throw new DataInvalidException();
        }
    }

    public void verificaValidade(String dataValidade) {
        LocalDateTime localDateTimeValidade = mudaParaISO(dataValidade);

        if (LocalDateTime.now().isAfter(localDateTimeValidade)) {
            throw new ForaDaValidadeException();
        }
    }
}
