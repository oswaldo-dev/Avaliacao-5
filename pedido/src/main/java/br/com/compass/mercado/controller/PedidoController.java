package br.com.compass.mercado.controller;

import br.com.compass.mercado.dto.request.RequestMessageDto;
import br.com.compass.mercado.dto.request.RequestPedidoDto;
import br.com.compass.mercado.dto.response.ResponsePedidoDto;
import br.com.compass.mercado.interfaces.AmqpService;
import br.com.compass.mercado.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Autowired
    private AmqpService amqpService;

    @GetMapping
    public ResponseEntity<Page<ResponsePedidoDto>> getAll(@RequestParam(required = false) String cpf,
                                                          @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable sort) {
        Page<ResponsePedidoDto> pedidos = service.getAll(cpf, sort);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePedidoDto> get(@PathVariable Long id) {
        ResponsePedidoDto responsePedidoDto = service.get(id);
        return ResponseEntity.ok(responsePedidoDto);
    }

    @PostMapping
    public ResponseEntity<ResponsePedidoDto> post(@RequestBody @Valid RequestPedidoDto pedido, UriComponentsBuilder componentsBuilder) {
        ResponsePedidoDto pedidoDto = service.post(pedido);
        URI uri = componentsBuilder.path("/api/pedido/{id}").buildAndExpand(pedidoDto.getId()).toUri();

        RequestMessageDto messageDto = new RequestMessageDto();
        messageDto.setId_pedido(pedidoDto.getId());
        messageDto.setTotal(pedidoDto.getTotal());

        amqpService.sendToConsumer(messageDto);
        return ResponseEntity.created(uri).body(pedidoDto);
    }

    @PatchMapping("/{id}/{total}")

    public ResponseEntity<Void> patch(@PathVariable BigDecimal total, @PathVariable Long id) {
        service.patch(id, total);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
