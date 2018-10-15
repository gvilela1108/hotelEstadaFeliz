package br.com.hotelEstadaFeliz.beans;

public class Login {

	private String email;
	private String senha;
	private Long idTipoFuncionario;
	
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
	public Long getIdTipoFuncionario() {
		return idTipoFuncionario;
	}
	public void setIdTipoFuncionario(Long idTipoFuncionario) {
		this.idTipoFuncionario = idTipoFuncionario;
	}
	
}
