package br.estudo.jms.dto.estudante;

import lombok.Data;

@Data
public class EnsinoSuperior {

	private Integer anoVinculo;
	private String codigoMatricula;
	private Long codigoIES;
	private Long codigoCurso;
	private Integer semestreIngresso;
	private Integer anoIngresso;
}
