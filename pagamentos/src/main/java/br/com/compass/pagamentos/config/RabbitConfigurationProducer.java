package br.com.compass.pagamentos.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class RabbitConfigurationProducer {

    private final String queue = "rk.producer.pagamento";
    @Value("${spring.rabbitmq.request.exchange.producer}")
    private String exchangeName;

    private final String deadLetter = "dl.producer.pagamento";

    private final ConnectionFactory factory;


    @PostConstruct
    public void createdRabbitElement() {
        log.info("Start created Rabbit configuration");
        RabbitAdmin admin = new RabbitAdmin(factory);
        createdDlQueue(admin);
        createdQueue(admin);
        log.info("End Rabbit config");
    }

    private void createdDlQueue(RabbitAdmin admin) {
        Queue DlQueue = QueueBuilder.durable(deadLetter).build();
        Binding binding = new Binding(deadLetter, Binding.DestinationType.QUEUE, exchangeName, deadLetter, null);

        admin.declareQueue(DlQueue);
        admin.declareBinding(binding);
    }

    private void createdQueue(RabbitAdmin admin) {
        Queue queue1 = QueueBuilder
                .durable(queue)
                .withArgument("x-dead-letter-exchange", exchangeName)
                .withArgument("x-dead-letter-routing-key", deadLetter)
                .build();
        Binding binding = new Binding(queue, Binding.DestinationType.QUEUE, exchangeName, queue, null);

        admin.declareQueue(queue1);
        admin.declareBinding(binding);
    }
}
