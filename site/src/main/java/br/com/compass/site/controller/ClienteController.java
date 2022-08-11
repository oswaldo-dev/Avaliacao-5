package br.com.compass.site.controller;

import br.com.compass.site.dto.request.RequestClienteDto;
import br.com.compass.site.dto.response.ResponseClienteDto;
import br.com.compass.site.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<ResponseClienteDto> post(@RequestBody @Valid RequestClienteDto clienteDto,
                                                   UriComponentsBuilder componentsBuilder) {
        ResponseClienteDto cliente = service.post(clienteDto);
        URI uri = componentsBuilder.path("/api/cliente/{cpf}").buildAndExpand(cliente.getCpf()).toUri();

        return ResponseEntity.created(uri).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ResponseClienteDto>> get() {
        List<ResponseClienteDto> clientes = service.get();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ResponseClienteDto> get(@PathVariable String cpf) {
        ResponseClienteDto cliente = service.get(cpf);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Void> put(@RequestBody @Valid RequestClienteDto clienteDto, @PathVariable String cpf) {
        service.put(clienteDto, cpf);
        return ResponseEntity.noContent().build();
    }

}
