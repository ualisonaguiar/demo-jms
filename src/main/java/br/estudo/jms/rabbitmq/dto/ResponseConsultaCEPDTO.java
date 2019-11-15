package br.estudo.jms.rabbitmq.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class ResponseConsultaCEPDTO {

	private HttpStatus status;
	private String body;

	public Boolean hasSuccess() {
		return !body().contains("CEP n\\u00e3o encontrado");
	}
}
