package br.com.compass.site.controller;

import br.com.compass.site.dto.request.RequestCartaoDto;
import br.com.compass.site.dto.response.ResponseCartaoDto;
import br.com.compass.site.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class CartaoController {

    @Autowired
    private CartaoService service;

    @PostMapping("/{clienteId}/cartoes")
    public ResponseEntity<ResponseCartaoDto> post(@RequestBody @Valid RequestCartaoDto cartao,
                                                  @PathVariable String clienteId, UriComponentsBuilder componentsBuilder) {
        ResponseCartaoDto cartaoDto = service.post(cartao, clienteId);
        URI uri = componentsBuilder.path("/api/cliente/{clienteId}/cartoes/{id}")
                .buildAndExpand(clienteId ,cartaoDto.getId()).toUri();

        return ResponseEntity.created(uri).body(cartaoDto);
    }

    @GetMapping("/{clienteId}/cartoes")
    public ResponseEntity<List<ResponseCartaoDto>> get(@PathVariable String clienteId) {
        List<ResponseCartaoDto> cartoes = service.get(clienteId);
        return ResponseEntity.ok(cartoes);
    }

    @GetMapping("/{clienteId}/cartoes/{id}")
    public ResponseEntity<ResponseCartaoDto> get(@PathVariable String clienteId, @PathVariable Long id) {
        ResponseCartaoDto cartao = service.get(clienteId, id);
        return ResponseEntity.ok(cartao);
    }

    @PutMapping("/{clienteId}/cartoes/{id}")
    public ResponseEntity<Void> put(@PathVariable String clienteId, @PathVariable Long id, @RequestBody @Valid RequestCartaoDto cartao) {
        service.put(clienteId, id, cartao);
        return ResponseEntity.noContent().build();
    }
}
