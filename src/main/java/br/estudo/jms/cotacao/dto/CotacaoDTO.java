package br.estudo.jms.cotacao.dto;

import lombok.Data;

@Data
public class CotacaoDTO {

	private Float cotacaoCompra;
	private Float cotacaoVenda;
	private String dataHoraCotacao;

}
