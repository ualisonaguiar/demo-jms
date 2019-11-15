package br.estudo.jms.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderQueueSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void send(String routingKey, String mensagem) {
		rabbitTemplate.convertAndSend(routingKey, mensagem);
	}
}