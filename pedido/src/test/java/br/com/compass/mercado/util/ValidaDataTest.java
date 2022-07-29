package br.com.compass.mercado.util;

import br.com.compass.mercado.exceptions.DataInvalidException;
import br.com.compass.mercado.exceptions.ForaDaValidadeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ValidaDataTest {

    private ValidaData data;

    @BeforeEach
    public void setUp() {
        this.data = new ValidaData();
    }

    @Test
    @DisplayName("Deveria retornar uma data no formato ISO")
    void mudaParaISO() {
        LocalDateTime localDate = data.mudaParaISO("11/02/2015 10:10:10");
        LocalDateTime dataTeste = LocalDateTime.of(2015, 2, 11, 10, 10, 10);

        assertEquals(dataTeste, localDate);
    }

    @Test
    @DisplayName("Deveria retornar uma data no formato BR")
    void mudaParaBr() {
        String localDate = data.mudaParaBr(LocalDateTime.of(2022, 7, 15, 10, 10, 10));
        String dataTeste = "15/07/2022 10:10:10";

        Assertions.assertEquals(dataTeste, localDate);
    }

    @Test
    @DisplayName("Deveria lançar uma DataInvalidException e caso de data errada")
    void informaDataErrada() {
        Assertions.assertThrows(DataInvalidException.class,() -> data.mudaParaISO("11/13/2005 10:10:10"));
    }

    @Test
    @DisplayName("Deve verificar se a data de criação é posterior a data de validade")
    void verificaData() {
        data.verificaData("12/12/2005 10:10:10", "11/12/2025 10:10:10");
    }

    @Test
    @DisplayName("Deveria lançar uma DataInvalidException caso data de criação seja posterior a data de validade")
    void verificaDataException() {
        Assertions.assertThrows(DataInvalidException.class,() -> data.verificaData("12/12/2005 10:10:10", "12/11/2005 10:10:10"));
    }

    @Test
    @DisplayName("Deve verificar se a data de validade é posterior a data de hoje")
    void verificaValidade() {
        LocalDateTime now = LocalDateTime.now().plusHours(1);
        String hora = data.mudaParaBr(now);

        data.verificaValidade(hora);
    }

    @Test
    @DisplayName("Deveria lançar uma ForaDaValidadeException caso data de validade seja posterior a data de hoje")
    void verificaValidadeException() {
        LocalDateTime now = LocalDateTime.now().minusHours(1);
        String hora = data.mudaParaBr(now);
        Assertions.assertThrows(ForaDaValidadeException.class,() -> data.verificaValidade(hora));
    }
}