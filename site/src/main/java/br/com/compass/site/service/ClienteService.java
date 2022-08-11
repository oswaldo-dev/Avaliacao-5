package br.com.compass.site.service;

import br.com.compass.site.dto.request.RequestClienteDto;
import br.com.compass.site.dto.response.ResponseClienteDto;
import br.com.compass.site.model.Cliente;
import br.com.compass.site.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ClienteRepository repository;

    public ResponseClienteDto post(RequestClienteDto clienteDto) {
        Cliente cliente = modelMapper.map(clienteDto, Cliente.class);
        if (repository.existsById(cliente.getCpf())) {
            throw new ResponseStatusException(HttpStatus.OK);
        }
        Cliente clienteEntity = repository.save(cliente);
        return modelMapper.map(clienteEntity, ResponseClienteDto.class);
    }

    public List<ResponseClienteDto> get() {
        List<Cliente> all = repository.findAll();
        return all.stream().map(cliente -> modelMapper.map(cliente, ResponseClienteDto.class))
                .collect(Collectors.toList());
    }

    public void put(RequestClienteDto clienteDto, String cpf) {
        Cliente clienteEntity = repository.findById(cpf).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        modelMapper.map(clienteDto, clienteEntity);
        repository.save(clienteEntity);

    }

    public ResponseClienteDto get(String cpf) {
        Cliente cliente = repository.findById(cpf).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));

        return modelMapper.map(cliente, ResponseClienteDto.class);

    }
}
