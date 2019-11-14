package br.estudo.jms.dto.estudante;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class EstudanteDTO {

	private String cpf;
	private String nome;
	private String nomeSocial;
	private LocalDate dataNascimento;
	private List<ResponsavelDTO> responsaveis = new ArrayList<>();
	private VinculoDTO vinculo;

}
