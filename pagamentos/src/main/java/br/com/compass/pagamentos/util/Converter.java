package br.com.compass.pagamentos.util;

import org.springframework.stereotype.Component;

@Component
public class Converter {
    public String converteIntParaString(Integer numero) {
        return numero.toString();
    }
}
