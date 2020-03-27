package br.estudo.jms.service;

import org.springframework.stereotype.Service;

@Service
public class EnviaCEPService {

//	@Autowired
//	OrderQueueSender sendQueue;
//
//	@Value("${queue.recebe.cep.name}")
//	private String recebeCep;
//
//	public boolean enviarCEPLote() {
//		FaixaCEPService.getFaixasCEPProcessar().values().forEach(listaCEP -> {
//			for (Integer cep = listaCEP.get(0); cep <= listaCEP.get(1); cep++) {
//				sendQueue.send(recebeCep, cep.toString());
//			}
//		});
//		return true;
//	}
//
	public boolean enviarCEPLote1() {
		return true;
	}
}
