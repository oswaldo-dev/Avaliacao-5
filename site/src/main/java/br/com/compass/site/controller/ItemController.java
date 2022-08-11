package br.com.compass.site.controller;

import br.com.compass.site.dto.request.RequestItemDto;
import br.com.compass.site.dto.response.ResponseItemDto;
import br.com.compass.site.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService service;

    @PostMapping
    public ResponseEntity<ResponseItemDto> post(@RequestBody @Valid RequestItemDto item,
                                                UriComponentsBuilder componentsBuilder) {
        ResponseItemDto itemDto = service.post(item);
        URI uri = componentsBuilder.path("/api/item/{id}").buildAndExpand(itemDto.getId()).toUri();

        return ResponseEntity.created(uri).body(itemDto);
    }

    @GetMapping
    public ResponseEntity<List<ResponseItemDto>> get() {
        List<ResponseItemDto> itens = service.get();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseItemDto> get(@PathVariable long id) {
        ResponseItemDto item = service.get(id);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@RequestBody @Valid RequestItemDto item, @PathVariable long id) {
        service.put(id, item);
        return ResponseEntity.noContent().build();
    }
}
