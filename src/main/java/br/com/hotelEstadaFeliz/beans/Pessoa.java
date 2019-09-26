package br.com.hotelEstadaFeliz.beans;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class Pessoa {

    protected String id;
    @NotNull(message="CPF obrigatorio")
    protected Long cpf;
    protected String nome;
    protected String telefone;
    protected String email;
    protected Endereco endereco;
	protected Date dataCriacao;
	protected Date dataAlteracao;
	
    public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Pessoa () {
    	super();
    }

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
    
    
}
