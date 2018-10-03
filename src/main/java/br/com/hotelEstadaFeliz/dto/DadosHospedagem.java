package br.com.hotelEstadaFeliz.dto;

import javax.validation.constraints.NotNull;

import br.com.hotelEstadaFeliz.beans.Hospedagem;

public class DadosHospedagem extends Hospedagem{

	@NotNull(message="Tipo Funcionário obrigatório")
	private Long idTipoFuncionario;
	
	public DadosHospedagem() {
		super();
	}

	public Long getIdTipoFuncionario() {
		return idTipoFuncionario;
	}

	public void setIdTipoFuncionario(Long idTipoFuncionario) {
		this.idTipoFuncionario = idTipoFuncionario;
	}
}
