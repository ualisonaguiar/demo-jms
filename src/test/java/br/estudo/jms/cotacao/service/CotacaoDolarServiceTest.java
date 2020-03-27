package br.estudo.jms.cotacao.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.estudo.jms.cotacao.dto.CotacaoDTO;
import br.estudo.jms.cotacao.dto.ResultCotacaoDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CotacaoDolarServiceTest {

	@Autowired
	CotacaoDolarService service;

	@Test
	public void getValorCorrente() throws URISyntaxException {
		assertNotNull(service.getValorAtual(LocalDate.now()));
	}

	@Test
	public void getValor25Marco2020() throws URISyntaxException {
		final ResultCotacaoDTO valorAtual = service.getValorAtual(LocalDate.of(2020, Month.MARCH, 25));
		final CotacaoDTO cotacaoDTO = valorAtual.getContacao().get(0);

		assertEquals(new Float(5.07), cotacaoDTO.getCotacaoCompra());
		assertEquals(new Float(5.0706), cotacaoDTO.getCotacaoVenda());
		assertEquals("2020-03-25 13:11:38.026", cotacaoDTO.getDataHoraCotacao());
	}

}
