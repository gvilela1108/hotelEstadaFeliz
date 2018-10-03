package br.com.hotelEstadaFeliz.dto;

import org.hibernate.validator.constraints.NotBlank;

public class DadosLogin {

	@NotBlank(message="Email obrigatorio")
	private String email;
	@NotBlank(message="Senha obrigatorio")
	private String senha;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
