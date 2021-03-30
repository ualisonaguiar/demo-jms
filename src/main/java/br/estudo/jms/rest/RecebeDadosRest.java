package br.estudo.jms.rest;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.estudo.jms.service.EnviaCEPService;
import lombok.AllArgsConstructor;

//@RestController
@AllArgsConstructor
public class RecebeDadosRest {

	EnviaCEPService cepService;

	// @GetMapping
	public String teste() throws JsonProcessingException {
		// cepService.enviarCEPLote();
		return "Teste de envio 000 090 0....";
	}
}
