package br.com.compass.mercado.service;

import br.com.compass.mercado.exceptions.ItemNotFoundException;
import br.com.compass.mercado.exceptions.NomeVazioException;
import br.com.compass.mercado.model.Item;
import br.com.compass.mercado.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {


    @Autowired
    private ItemRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public void patch(Long id, String nome) {
        Item itemEntity = repository.findById(id).orElseThrow(ItemNotFoundException::new);
        if (!nome.isBlank()) {
            itemEntity.setNome(nome);
            repository.save(itemEntity);
        } else {
            throw new NomeVazioException();
        }
    }
}
