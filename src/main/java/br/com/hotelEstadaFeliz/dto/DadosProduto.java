package br.com.hotelEstadaFeliz.dto;

import javax.validation.constraints.NotNull;

import br.com.hotelEstadaFeliz.beans.Produto;

public class DadosProduto extends Produto{

	@NotNull(message="Tipo Funcionário obrigatório")
	private Long idTipoFuncionario;
	
	public DadosProduto() {
		super();
	}
	
	public Long getIdTipoFuncionario() {
		return idTipoFuncionario;
	}
	public void setIdTipoFuncionario(Long idTipoFuncionario) {
		this.idTipoFuncionario = idTipoFuncionario;
	}
}
