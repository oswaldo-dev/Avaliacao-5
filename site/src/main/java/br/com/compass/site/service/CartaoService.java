package br.com.compass.site.service;

import br.com.compass.site.dto.request.RequestCartaoDto;
import br.com.compass.site.dto.response.ResponseCartaoDto;
import br.com.compass.site.enums.Marca;
import br.com.compass.site.model.Cartao;
import br.com.compass.site.model.Cliente;
import br.com.compass.site.repository.CartaoRepository;
import br.com.compass.site.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ModelMapper modelMapper;


    public ResponseCartaoDto post(RequestCartaoDto cartao, String clienteId) {
        validaMarca(cartao.getMarca());

        Cartao cartaoEntity = modelMapper.map(cartao, Cartao.class);
        Cartao cartaoSave = cartaoRepository.save(cartaoEntity);

        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        cliente.getCartaos().add(cartaoEntity);
        clienteRepository.save(cliente);

        return modelMapper.map(cartaoSave, ResponseCartaoDto.class);
    }

    public List<ResponseCartaoDto> get(String id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Cartao> cartaos = cliente.getCartaos();

        return cartaos.stream().map(cartao -> modelMapper.map(cartao, ResponseCartaoDto.class))
                .collect(Collectors.toList());
    }

    public ResponseCartaoDto get(String clienteId, Long id) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));

        Cartao cartao = cartaoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));


        return modelMapper.map(cartao, ResponseCartaoDto.class);
    }

    public void put(String clienteId, Long id, RequestCartaoDto cartao) {
        validaMarca(cartao.getMarca());

        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));

        Cartao cartaoEntity = cartaoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));


        modelMapper.map(cartao, cartaoEntity);
        cartaoRepository.save(cartaoEntity);

    }

    private void validaMarca(String marca) {
        try {
            Marca.valueOf(marca);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
