package br.com.compass.pagamentos.service;

import br.com.compass.pagamentos.dto.request.*;
import br.com.compass.pagamentos.dto.response.ResponseAuthDto;
import br.com.compass.pagamentos.dto.response.ResponseBancoDto;
import br.com.compass.pagamentos.dto.response.ResponseMenssageDto;
import br.com.compass.pagamentos.interfaces.AmqpProducer;
import br.com.compass.pagamentos.interfaces.AmqpService;
import br.com.compass.pagamentos.interfaces.ConsumerService;
import br.com.compass.pagamentos.model.Pagamento;
import br.com.compass.pagamentos.repository.PagamentoRepository;
import br.com.compass.pagamentos.util.Converter;
import br.com.compass.pagamentos.util.Criptografar;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalTime;

@Service
@Slf4j
public class PagamentoService implements ConsumerService, AmqpService {

    @Autowired
    public PagamentoRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private WebClient.Builder webClient;
    @Autowired
    private Criptografar criptografar;
    @Autowired
    private Converter converter;
    @Autowired
    private AmqpProducer<RequestMessageDto> amqp;
    @Autowired
    private AmqpService amqpService;
    private String accessToken;
    private LocalTime localTime;

    @Override
    public void action(ResponseMenssageDto message) throws Exception {
        Pagamento pagamento = modelMapper.map(message, Pagamento.class);
        repository.save(pagamento);

        log.info("==========PEDIDO==========\n" +
                "id : " + message.getIdPedido() +
                "\ntotal :" + message.getTotal() +
                "\n---------------------------\n");

        ResponseBancoDto responseBancoDto = enviaPagamentoBanco(message);

        RequestMessageDto messageDto = RequestMessageDto.builder()
                .pedidoId(message.getIdPedido())
                .status(responseBancoDto.getStatus())
                .build();

        amqpService.sendToConsumer(messageDto);

    }

    private ResponseAuthDto autenticador() {
        RequestAuthDto oswaldo = RequestAuthDto.builder()
                .clientId("client_id_oswaldo")
                .apiKey("ffa0400d-35f3-4552-af99-2930637c50af")
                .build();

        String uri = "https://pb-getway-payment.herokuapp.com/v1/auth";

        return webClient.build()
                .post().uri(uri)
                .bodyValue(oswaldo)
                .retrieve()
                .bodyToMono(ResponseAuthDto.class)
                .block();
    }

    private ResponseBancoDto enviaPagamentoBanco(ResponseMenssageDto message) {
        RequestBancoDto banco = RequestBancoDto.builder()
                .sellerId("320e2b0a-d37b-4ed5-a526-df6ddec01ae9")
                .customer(
                        RequestCustomerDto.builder()
                                .documentType("CPF").documentNumber(message.getCpf())
                                .build()
                )
                .paymentType("CREDIT_CARD")
                .currency("BRL")
                .transactionAmount(message.getTotal().doubleValue())
                .card(
                        RequestCardDto.builder()
                                .numberToken(criptografar.criptografaParaMD5(message.getCartao().getNumeroCartao()))
                                .cardholderName(message.getCartao().getNomeCartao())
                                .securityCode(message.getCartao().getCodigoSeguranca())
                                .brand(message.getCartao().getMarca().toString())
                                .expirationMonth(converter.converteIntParaString(message.getCartao().getMesExpiracao()))
                                .expirationYear(converter.converteIntParaString(message.getCartao().getAnoExpiracao()))
                                .build()
                )
                .build();

        String uri = "https://pb-getway-payment.herokuapp.com/v1/payments/credit-card";


        if (localTime == null) {
            this.accessToken = autenticador().getAccessToken();
            this.localTime = LocalTime.now();
        }

        if (localTime.isAfter(localTime.plusMinutes(3))) {
            this.accessToken = autenticador().getAccessToken();
            this.localTime = LocalTime.now();
        }


        return webClient.build()
                .post().uri(uri)
                .bodyValue(banco)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(ResponseBancoDto.class)
                .block();
    }

    @Override
    public void sendToConsumer(RequestMessageDto messageDto) {
        amqp.producer(messageDto);
    }
}
