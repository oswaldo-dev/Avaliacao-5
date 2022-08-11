package br.com.compass.site.service;

import br.com.compass.site.dto.request.RequestItemDto;
import br.com.compass.site.dto.response.ResponseItemDto;
import br.com.compass.site.model.Item;
import br.com.compass.site.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ItemRepository repository;


    public ResponseItemDto post(RequestItemDto item) {
        Item itemEntity = modelMapper.map(item, Item.class);
        itemEntity.setSkuid("osf" + criaId());
        Item itemSave = repository.save(itemEntity);
        return modelMapper.map(itemSave, ResponseItemDto.class);
    }

    public List<ResponseItemDto> get() {
        List<Item> itens = repository.findAll();
        return itens.stream().map(item -> modelMapper.map(item, ResponseItemDto.class)).collect(Collectors.toList());
    }

    public ResponseItemDto get(long id) {
        Item item = repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.map(item, ResponseItemDto.class);
    }

    public void put(long id, RequestItemDto item) {
        Item itemEntity = repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        modelMapper.map(item, itemEntity);
        repository.save(itemEntity);
    }

    private Long criaId() {
        long sorteado = new Random().nextLong();

        if (sorteado < 0 ) {
            sorteado *= -1;
        }

        while (repository.existsBySkuid("osf" + sorteado)) {
            sorteado += 1;
        }
        return sorteado;
    }
}
