package br.estudo.jms.rest;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.estudo.jms.dto.estudante.EstudanteDTO;
import br.estudo.jms.helper.ConvertJson;
import br.estudo.jms.rabbitmq.OrderQueueSender;

@RestController
public class RecebeDadosRest {

	@Autowired
	OrderQueueSender orderQueueSender;

	@GetMapping
	public String teste() throws JsonProcessingException {

		EstudanteDTO estudo = new EstudanteDTO();

		estudo.setCpf("02182841105");
		estudo.setDataNascimento(LocalDate.now());
		estudo.setNome("Ualison Aguiar");
		estudo.setNomeSocial("");

		for (int i = 0; i <= 100000; i++) {
			orderQueueSender.send(ConvertJson.mapToJson(estudo));
		}
		return "Teste de envio....";
	}
}
