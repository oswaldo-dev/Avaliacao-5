package br.com.compass.mercado.service;

import br.com.compass.mercado.dto.request.RequestItemDto;
import br.com.compass.mercado.dto.request.RequestPedidoDto;
import br.com.compass.mercado.dto.response.ResponsePedidoDto;
import br.com.compass.mercado.enums.Status;
import br.com.compass.mercado.exceptions.PedidoNotFoundException;
import br.com.compass.mercado.exceptions.ValorDeDescontoInvalidoException;
import br.com.compass.mercado.model.Pedido;
import br.com.compass.mercado.repository.PedidoRepository;
import br.com.compass.mercado.util.ValidaData;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ValidaData data;

    public Page<ResponsePedidoDto> getAll(String cpf, Pageable sort) {
        if (cpf == null) {
            Page<Pedido> all = repository.findAll(sort);
            return all.map(pedidos -> modelMapper.map(pedidos, ResponsePedidoDto.class));
        }
        Page<Pedido> all = repository.findByCpf(cpf, sort);
        return all.map(pedidos -> modelMapper.map(pedidos, ResponsePedidoDto.class));
    }

    public ResponsePedidoDto get(Long id) {
        Pedido pedido = repository.findById(id).orElseThrow(PedidoNotFoundException::new);
        return modelMapper.map(pedido, ResponsePedidoDto.class);
    }

    public ResponsePedidoDto post(RequestPedidoDto pedido) {
        validaData(pedido);
        BigDecimal total = pedido.getItens().stream().map(RequestItemDto::getValor).reduce(BigDecimal.ZERO,BigDecimal::add);
        Pedido pedidoEntity = modelMapper.map(pedido, Pedido.class);

        if (pedido.getTotal() != total) {
            pedidoEntity.setTotal(total);
        }

        Pedido pedidoSalvo = repository.save(pedidoEntity);
        return modelMapper.map(pedidoSalvo, ResponsePedidoDto.class);
    }

    public void patch(Long id,  BigDecimal total) {
        Pedido pedidoEntity = repository.findById(id).orElseThrow(PedidoNotFoundException::new);
        if (total.compareTo(new BigDecimal(0)) != -1) {
            pedidoEntity.setTotal(total);
            repository.save(pedidoEntity);
        } else {
            throw new ValorDeDescontoInvalidoException();
        }
    }

    public void delete(Long id) {
        Pedido pedidoEntity = repository.findById(id).orElseThrow(PedidoNotFoundException::new);
        repository.delete(pedidoEntity);
    }

    private void validaData(RequestPedidoDto pedido) {
        RequestItemDto itemAtual = pedido.getItens().get(pedido.getItens().size() - 1);
        String dataDeCriacaoOferta = itemAtual.getOfertas().get(itemAtual.getOfertas().size() - 1).getDataDeCriacao();
        String dataDeValidadeOferta = itemAtual.getOfertas().get(itemAtual.getOfertas().size() - 1).getDataDeValidade();

        data.verificaData(dataDeCriacaoOferta, dataDeValidadeOferta);
        data.verificaValidade(dataDeValidadeOferta);
    }
}
