package br.estudo.jms.dto.estudante;

import lombok.Data;

@Data
public class EnsinoBasico {

	private Integer anoVinculo;
	private String matricula;
	private Long codigoEscola;
	private Long codigoEtapaSerie;
}
