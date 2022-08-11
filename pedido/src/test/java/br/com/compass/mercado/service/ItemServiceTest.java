package br.com.compass.mercado.service;

import br.com.compass.mercado.dto.request.RequestItemDto;
import br.com.compass.mercado.dto.request.RequestOfertaDto;
import br.com.compass.mercado.model.Item;
import br.com.compass.mercado.repository.ItemRepository;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class ItemServiceTest {

    @InjectMocks
    private ItemService service;
    @Mock
    private ItemRepository repository;
    @Mock
    private ModelMapper modelMapper;

    private Item item;
    private RequestItemDto itemDto;
    private List<RequestOfertaDto> ofertas;

    @BeforeEach
    public void setUp() {
        this.item = new Item();
        this.ofertas = new ArrayList<>();

        ofertas.add(RequestOfertaDto.builder()
                .nome("CPSS10")
                .dataCriacao(LocalDateTime.parse("10/10/2015 10:10:10"))
                .dataValidade(LocalDateTime.parse("11/11/2025 10:10:10"))
                .desconto(new BigDecimal("10.0"))
                .descricao("10 reais de desconto").build());


        this.itemDto = RequestItemDto.builder()
                .nome("Carro")
                .ofertas(ofertas)
                .valor(new BigDecimal(100))
                .descricao("Honda Civic")
                .dataValidade(LocalDateTime.parse("11/11/2025 10:10:10"))
                .dataCriacao(LocalDateTime.parse("10/10/2015 10:10:10")).build();
    }


    @Test
    @DisplayName("Deveria atualizar o nome de um item.")
    void patch() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.ofNullable(this.item));

        service.patch(1L, "Honda Fit");
        Mockito.verify(repository).save(this.item);
    }
}