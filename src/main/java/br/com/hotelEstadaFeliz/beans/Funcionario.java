package br.com.hotelEstadaFeliz.beans;

import javax.validation.constraints.NotNull;

public class Funcionario extends Pessoa{
	
	private String senha;
	private Float salario;
	@NotNull(message="Tipo Função 1-> Proprietario 2-> Recepção obrigatório")
	private Long tipoFuncionario;
    private String erroFuncionario;
	
    public Funcionario() {
		super();
	}
    
	public Long getTipoFuncionario() {
		return tipoFuncionario;
	}
	public void setTipoFuncionario(Long tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}
	public Float getSalario() {
		return salario;
	}
	public void setSalario(Float salario) {
		this.salario = salario;
	}

	public String getErroFuncionario() {
		return erroFuncionario;
	}

	public void setErroFuncionario(String erroFuncionario) {
		this.erroFuncionario = erroFuncionario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

    
}
