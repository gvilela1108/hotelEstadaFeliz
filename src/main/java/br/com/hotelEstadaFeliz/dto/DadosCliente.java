package br.com.hotelEstadaFeliz.dto;

import javax.validation.constraints.NotNull;

import br.com.hotelEstadaFeliz.beans.Pessoa;

public class DadosCliente extends Pessoa{
	
	@NotNull(message="Tipo Funcionario obrigatorio")
	private Long idTipoFuncionario;
	
	public DadosCliente() {
		super();
	}

	public Long getIdTipoFuncionario() {
		return idTipoFuncionario;
	}

	public void setIdTipoFuncionario(Long idTipoFuncionario) {
		this.idTipoFuncionario = idTipoFuncionario;
	}
}
