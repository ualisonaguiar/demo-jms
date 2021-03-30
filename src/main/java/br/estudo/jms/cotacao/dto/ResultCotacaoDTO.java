package br.estudo.jms.cotacao.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties("@odata.context")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ResultCotacaoDTO {

	@JsonProperty("value")
	private List<CotacaoDTO> contacao;
}
