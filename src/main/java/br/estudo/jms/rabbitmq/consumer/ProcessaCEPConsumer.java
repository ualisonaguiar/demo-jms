package br.estudo.jms.rabbitmq.consumer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.estudo.jms.rabbitmq.OrderQueueSender;
import br.estudo.jms.rabbitmq.dto.ResponseConsultaCEPDTO;

@Component
public class ProcessaCEPConsumer {

	@Autowired
	OrderQueueSender sendQueue;

	@Value("${webservice.url.correios}")
	private String urlWebService;

	@Value("${queue.falha.busca.cep.name}")
	private String queueFalha;

	@Value("${queue.carga.banco.name}")
	private String queueCargaBanco;

	// @RabbitListener(queues = { "${queue.cluster.no.name}" })
	public void receberCep(@Payload String mensagem) throws URISyntaxException, InterruptedException {
		consultaCEP(mensagem);
	}

	public boolean consultaCEP(final String cep) throws URISyntaxException {
		ResponseConsultaCEPDTO responseWebService = consultaCEPWebService(cep);
		Boolean situacao = false;
		String routingKey = queueFalha;
		String mensagem = cep;
		if (responseWebService.hasSuccess()) {
			routingKey = queueCargaBanco;
			mensagem = responseWebService.body();
			situacao = true;
		}
		sendQueue.send(routingKey, mensagem);
		return situacao;
	}

	public ResponseConsultaCEPDTO consultaCEPWebService(final String cep) throws URISyntaxException {
		RestTemplate rest = new RestTemplate();
		URI uri = new URI(urlWebService.concat("/").concat(cep).concat(".json"));
		ResponseConsultaCEPDTO responseDTO = new ResponseConsultaCEPDTO();
		try {
			ResponseEntity<String> response = rest.exchange(uri, HttpMethod.GET, prepareHeader(), String.class);
			responseDTO.status(response.getStatusCode()).body(response.getBody());
		} catch (HttpClientErrorException ex) {
			responseDTO.status(ex.getStatusCode());
		}
		return responseDTO;
	}

	protected HttpEntity<String> prepareHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}
}
