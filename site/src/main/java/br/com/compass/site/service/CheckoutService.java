package br.com.compass.site.service;

import br.com.compass.site.dto.request.RequestCartaoDto;
import br.com.compass.site.dto.request.RequestCheckoutDto;
import br.com.compass.site.dto.request.RequestItemCheckoutDto;
import br.com.compass.site.dto.request.RequestPedidoDto;
import br.com.compass.site.dto.response.ResponseCheckoutDto;
import br.com.compass.site.dto.response.ResponseItemDto;
import br.com.compass.site.exception.CartaoNotFoundException;
import br.com.compass.site.exception.EstoqueInsuficienteException;
import br.com.compass.site.exception.ItemNotFoundException;
import br.com.compass.site.model.Cartao;
import br.com.compass.site.model.Cliente;
import br.com.compass.site.model.Item;
import br.com.compass.site.repository.ClienteRepository;
import br.com.compass.site.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckoutService {

    @Autowired
    private WebClient.Builder webClient;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseCheckoutDto post(RequestCheckoutDto checkout) {
        Cliente cliente = clienteRepository.findById(checkout.getCliente().getClienteId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<ResponseItemDto> itens = getResponseItemDtos(checkout);

        RequestCartaoDto cartaoDto = devolveCartao(checkout, cliente);

        RequestPedidoDto pedido = RequestPedidoDto.builder()
                .cpf(cliente.getCpf())
                .itens(itens)
                .tipoPagamento("CREDIT_CARD")
                .cartao(cartaoDto)
                .total(calculaTotal(checkout.getItens()))
                .build();

        String uri = "http://localhost:8080/api/pedido";

        return webClient.build().post()
                .uri(uri).bodyValue(pedido)
                .retrieve().bodyToMono(ResponseCheckoutDto.class).block();

    }

    private List<ResponseItemDto> getResponseItemDtos(RequestCheckoutDto checkout) {
        List<ResponseItemDto> itens = new ArrayList<>();

        for (RequestItemCheckoutDto itemEscolhido : checkout.getItens()) {
            try {
                Item item = itemRepository.findBySkuid(itemEscolhido.getSkuId());
                int estoque = item.getEstoque();
                int quantidade = itemEscolhido.getQuantidade();
                if (estoque < quantidade) {
                    throw new EstoqueInsuficienteException();
                }
                ResponseItemDto itemBuild = ResponseItemDto.builder()
                        .id(item.getId())
                        .nome(item.getNome())
                        .dataValidade(item.getDataValidade())
                        .dataCriacao(item.getDataCriacao())
                        .valor(item.getValor())
                        .descricao(item.getDescricao())
                        .estoque(item.getEstoque())
                        .skuid(item.getSkuid())
                        .build();
                item.setEstoque(item.getEstoque() - quantidade);
                itemRepository.save(item);
                itens.add(itemBuild);
            } catch (NullPointerException e) {
                throw new ItemNotFoundException();
            }
        }
        return itens;
    }

    private RequestCartaoDto devolveCartao(RequestCheckoutDto checkout, Cliente cliente) {
        for (Cartao cartao : cliente.getCartaos()) {
            long id = cartao.getId();

            if (id == checkout.getCliente().getCartaoId()) {
                return RequestCartaoDto.builder()
                        .numero(cartao.getNumero())
                        .nome(cartao.getNome())
                        .codigo(cartao.getCodigo())
                        .marca(String.valueOf(cartao.getMarca()))
                        .mesValidade(cartao.getMesValidade())
                        .anoValidade(cartao.getAnoValidade())
                        .build();
            }
        }

        throw new CartaoNotFoundException();
    }

    private BigDecimal calculaTotal(List<RequestItemCheckoutDto> itensCheckout) {
        BigDecimal total = BigDecimal.ZERO;
        for (RequestItemCheckoutDto itemQuantidade : itensCheckout) {
            Item itemEntity = itemRepository.findBySkuid(itemQuantidade.getSkuId());
            BigDecimal valor = itemEntity.getValor();
            BigDecimal multiply = valor.multiply(BigDecimal.valueOf(itemQuantidade.getQuantidade()));
            total = total.add(multiply);
        }

        return total;
    }
}
