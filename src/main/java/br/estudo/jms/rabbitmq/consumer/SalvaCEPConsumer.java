package br.estudo.jms.rabbitmq.consumer;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SalvaCEPConsumer {

	// @RabbitListener(queues = { "${queue.salvar.cep}" })
	public void salvar(String json) {
		SalvaCEPConsumer.log.info("************ Salvando CEP ****************");
		SalvaCEPConsumer.log.info(json);
		SalvaCEPConsumer.log.info("****************************");
	}

}
