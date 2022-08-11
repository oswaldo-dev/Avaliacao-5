package br.com.compass.mercado.service;

import br.com.compass.mercado.dto.request.RequestPedidoDto;
import br.com.compass.mercado.dto.response.ResponseMenssageDto;
import br.com.compass.mercado.dto.response.ResponsePedidoDto;
import br.com.compass.mercado.enums.Status;
import br.com.compass.mercado.enums.StatusPagamento;
import br.com.compass.mercado.exceptions.PedidoIndelibleException;
import br.com.compass.mercado.exceptions.PedidoNotFoundException;
import br.com.compass.mercado.exceptions.ValorDeDescontoInvalidoException;
import br.com.compass.mercado.interfaces.ConsumerService;
import br.com.compass.mercado.model.Pedido;
import br.com.compass.mercado.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PedidoService implements ConsumerService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

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

        Pedido pedidoEntity = modelMapper.map(pedido, Pedido.class);

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

        if (pedidoEntity.getStatusPagamento().equals(StatusPagamento.APROVADO)
                || pedidoEntity.getStatusPagamento().equals(StatusPagamento.REJEITADO) ) {
            throw new PedidoIndelibleException();
        }

        repository.delete(pedidoEntity);
    }

    @Override
    public void action(ResponseMenssageDto message) throws Exception {
        Pedido pedido = repository.findById(message.getPedidoId()).orElseThrow(PedidoNotFoundException::new);

        if (message.getStatus().equals("APPROVED")) {
            pedido.setStatus(Status.FINALIZADO);
            pedido.setStatusPagamento(StatusPagamento.APROVADO);
        } else {
            pedido.setStatus(Status.CANCELADO);
            pedido.setStatusPagamento(StatusPagamento.REJEITADO);
        }

        repository.save(pedido);
    }
}
