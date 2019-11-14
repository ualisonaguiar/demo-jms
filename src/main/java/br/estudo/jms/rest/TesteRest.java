package br.estudo.jms.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteRest {

	@GetMapping
	public String teste() {
		return "Hello World;;;;;;";
	}
}
