package br.estudo.jms.dto.estudante;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class VinculoDTO {

	private final List<EnsinoBasico> instituicoesEnsinoBasico = new ArrayList<>();
	private final List<EnsinoSuperior> instituicoesEnsinoSuperior = new ArrayList<>();
}
