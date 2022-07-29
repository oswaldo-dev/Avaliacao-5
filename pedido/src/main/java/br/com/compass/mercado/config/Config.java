package br.com.compass.mercado.config;

import br.com.compass.mercado.util.ValidaData;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class Config {
    @Autowired
    private ValidaData data;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, LocalDateTime> stringParaLocalDateTime = getStringLocalDateConverter();

        Converter<LocalDateTime, String> localDateTimeParaString = getLocalDateStringConverter();

        modelMapper.createTypeMap(String.class, LocalDateTime.class);
        modelMapper.createTypeMap(LocalDateTime.class, String.class);
        modelMapper.addConverter(stringParaLocalDateTime);
        modelMapper.addConverter(localDateTimeParaString);

        return modelMapper;

    }

    private Converter<LocalDateTime, String> getLocalDateStringConverter() {
        return new AbstractConverter<LocalDateTime, String>() {

            protected String convert(LocalDateTime localDate) {
                return data.mudaParaBr(localDate);
            }
        };
    }

    private Converter<String, LocalDateTime> getStringLocalDateConverter() {
        return new AbstractConverter<String, LocalDateTime>() {

            protected LocalDateTime convert(String string) {
                return data.mudaParaISO(string);
            }
        };
    }
}
