package br.estudo.jms.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnviaCEPServiceTest {

	@Autowired
	EnviaCEPService enviaCEPService;

	@Test
	public void teste() {
		assertTrue(enviaCEPService.enviarCEPLote());
	}

}
