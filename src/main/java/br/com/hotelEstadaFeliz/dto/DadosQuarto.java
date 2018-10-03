package br.com.hotelEstadaFeliz.dto;

import javax.validation.constraints.NotNull;

import br.com.hotelEstadaFeliz.beans.Quarto;

public class DadosQuarto extends Quarto{
    
	@NotNull(message="Tipo Funcionário obrigatório")
	private Long idTipoFuncionario;
	
	public DadosQuarto() {
		super();
	}
	
	public Long getIdTipoFuncionario() {
		return idTipoFuncionario;
	}
	public void setIdTipoFuncionario(Long idTipoFuncionario) {
		this.idTipoFuncionario = idTipoFuncionario;
	}
}
