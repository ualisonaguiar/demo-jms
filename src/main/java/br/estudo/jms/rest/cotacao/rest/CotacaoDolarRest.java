package br.estudo.jms.rest.cotacao.rest;

import java.net.URISyntaxException;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.estudo.jms.cotacao.dto.ResultCotacaoDTO;
import br.estudo.jms.cotacao.service.CotacaoDolarService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CotacaoDolarRest {

	CotacaoDolarService cotacaoDolar;

	@GetMapping
	public ResultCotacaoDTO getValorAtual(@RequestParam(name = "data") @DateTimeFormat(iso = ISO.DATE) LocalDate data)
			throws URISyntaxException {
		return cotacaoDolar.getValorAtual(data);
	}

}
