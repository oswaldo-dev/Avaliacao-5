package br.com.compass.pagamentos.service.implementation;

import br.com.compass.pagamentos.dto.ResponseMenssageDto;
import br.com.compass.pagamentos.model.Pagamento;
import br.com.compass.pagamentos.repository.PagamentoRepository;
import br.com.compass.pagamentos.service.ConsumerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService implements ConsumerService {

    @Autowired
    PagamentoRepository repository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public void action(ResponseMenssageDto message) throws Exception {
        Pagamento pagamento = modelMapper.map(message, Pagamento.class);
        repository.save(pagamento);

        System.out.println("==========PEDIDO==========\n" +
                "id : " + message.getId_pedido() +
                "\ntotal :" + message.getTotal() +
                "\n---------------------------\n");
    }
}
