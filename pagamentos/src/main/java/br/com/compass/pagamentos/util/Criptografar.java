package br.com.compass.pagamentos.util;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Criptografar {

    public String criptografaParaMD5(String numeroCartao){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(numeroCartao.getBytes(StandardCharsets.UTF_8)));

            return String.format("%32x", hash);
        } catch (NoSuchAlgorithmException exception) {
            throw new RuntimeException(exception.getMessage());
        }



    }

}
