package br.com.hotelEstadaFeliz.dto;

import javax.validation.constraints.NotNull;

import br.com.hotelEstadaFeliz.beans.Funcionario;

public class DadosFuncionario extends Funcionario{

	@NotNull(message="Tipo Funcionario obrigatorio")
	private Long idTipoFuncionario;
	
	public DadosFuncionario() {
		super();
	}

	public Long getIdTipoFuncionario() {
		return idTipoFuncionario;
	}

	public void setIdTipoFuncionario(Long idTipoFuncionario) {
		this.idTipoFuncionario = idTipoFuncionario;
	}
	
}
