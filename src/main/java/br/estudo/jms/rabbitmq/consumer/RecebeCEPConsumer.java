package br.estudo.jms.rabbitmq.consumer;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.estudo.jms.rabbitmq.OrderQueueSender;
import br.estudo.jms.service.FaixaCEPService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RecebeCEPConsumer {

	OrderQueueSender sendQueue;

	// @RabbitListener(queues = { "${queue.recebe.cep.name}" })
	public void recebeCEP(@Payload String fileBody) throws InterruptedException {
		classificaEnviaCEP(fileBody);
	}

	public void classificaEnviaCEP(final String cepMessageria) {
		Integer cep = Integer.parseInt(cepMessageria);
		FaixaCEPService.getFaixasCEPProcessar().forEach((siglaUF, listaFaixaCEP) -> {
			if (listaFaixaCEP.get(0) >= cep || cep <= listaFaixaCEP.get(1)) {
				sendQueue.send("cep_".concat(siglaUF), cepMessageria);
			}
		});
	}

}