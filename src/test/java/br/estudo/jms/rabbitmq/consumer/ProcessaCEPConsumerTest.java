package br.estudo.jms.rabbitmq.consumer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import br.estudo.jms.rabbitmq.dto.ResponseConsultaCEPDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessaCEPConsumerTest {

	@Autowired
	ProcessaCEPConsumer processaCEPConsumer;

	@Test
	public void consultaCEPWebService() throws URISyntaxException {
		ResponseConsultaCEPDTO consultaCEP = processaCEPConsumer.consultaCEPWebService("70058900");
		assertEquals(consultaCEP.status(), HttpStatus.OK);
		assertNotNull(consultaCEP.body());
		assertTrue(consultaCEP.hasSuccess());
	}

	@Test
	public void consultaCEPWebServiceErro() throws URISyntaxException {
		ResponseConsultaCEPDTO consultaCEP = processaCEPConsumer.consultaCEPWebService("00000000");
		assertEquals(consultaCEP.status(), HttpStatus.OK);
		assertFalse(consultaCEP.hasSuccess());
	}

	@Test
	public void consultaCEP() throws URISyntaxException {
		boolean consultaCEP = processaCEPConsumer.consultaCEP("70058900");
		assertTrue(consultaCEP);
	}

	@Test
	public void consultaCEPError() throws URISyntaxException {
		boolean consultaCEP = processaCEPConsumer.consultaCEP("00000000");
		assertNotNull(consultaCEP);
	}
}
