package br.com.hotelEstadaFeliz.dto;

import javax.validation.constraints.NotNull;

import br.com.hotelEstadaFeliz.beans.Hotel;

public class DadosHotel extends Hotel {

	@NotNull(message="Tipo Funcion�rio obrigat�rio")
	private Long idTipoFuncionario;
	
	public DadosHotel() {
		super();
	}

	public Long getIdTipoFuncionario() {
		return idTipoFuncionario;
	}

	public void setIdTipoFuncionario(Long idTipoFuncionario) {
		this.idTipoFuncionario = idTipoFuncionario;
	}
}
