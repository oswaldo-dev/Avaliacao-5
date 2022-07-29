package br.com.compass.mercado.service;

import br.com.compass.mercado.dto.request.RequestItemDto;
import br.com.compass.mercado.dto.request.RequestOfertaDto;
import br.com.compass.mercado.dto.request.RequestPedidoDto;
import br.com.compass.mercado.model.Pedido;
import br.com.compass.mercado.repository.PedidoRepository;
import br.com.compass.mercado.util.ValidaData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class PedidoServiceTest {

    @InjectMocks
    private PedidoService service;
    @Mock
    private PedidoRepository repository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ValidaData data;
    private Pedido pedido;
    private RequestPedidoDto pedidoDto;
    private List<RequestItemDto> itens;
    private List<RequestOfertaDto> ofertas;

    @BeforeEach
    public void setUp() {
        this.pedido = new Pedido();
        this.pedido.setId(1L);
        this.itens = new ArrayList<>();
        this.ofertas = new ArrayList<>();

        ofertas.add(RequestOfertaDto.builder()
                .nome("CPSS10")
                .dataDeCriacao("10/10/2010 10:10:10")
                .dataDeValidade("10/10/2025 10:10:10")
                .desconto(new BigDecimal("10.0"))
                .descricao("10 reais de desconto").build());
        itens.add(RequestItemDto.builder()
                .nome("Video-Game")
                .dataDeCriacao("10/10/2010 10:10:10")
                .dataDeValidade("10/10/2025 10:10:10")
                .valor(new BigDecimal("10.0"))
                .descricao("PlayStation").ofertas(ofertas).build());

        this.pedidoDto = RequestPedidoDto.builder().cpf("07147642058").itens(itens).build();
    }

    @Test
    @DisplayName("Deveria pegar um pedido pelo id")
    void get() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.ofNullable(this.pedido));

        service.get(1L);
        Mockito.verify(repository).findById(this.pedido.getId());
    }

    @Test
    @DisplayName("Deveria cadastrar um pedido")
    void post() {
        Mockito.when(modelMapper.map(this.pedidoDto, Pedido.class)).thenReturn(this.pedido);

        service.post(this.pedidoDto);
        Mockito.verify(repository).save(this.pedido);
    }

    @Test
    @DisplayName("Deveria atualizar o valor total do pedido")
    void patch() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.ofNullable(this.pedido));

        service.patch(1L, new BigDecimal(15));
        Mockito.verify(repository).save(this.pedido);

    }

    @Test
    @DisplayName("Deveria deletar um pedido")
    void delete() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.ofNullable(this.pedido));

        service.delete(1L);
        Mockito.verify(repository).delete(this.pedido);

    }
}