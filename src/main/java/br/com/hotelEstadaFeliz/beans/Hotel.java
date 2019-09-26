package br.com.hotelEstadaFeliz.beans;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class Hotel {

    private String id;
    private String nome;
    private Endereco endereco;
    @NotNull(message="CNPJ obrigatorio")
    private Long cnpj;
    private String telefone;
    private Date dataCriacao;
    private Date dataAlteracao;
    private String erroHotel;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Long getCnpj() {
		return cnpj;
	}
	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public String getErroHotel() {
		return erroHotel;
	}
	public void setErroHotel(String erroHotel) {
		this.erroHotel = erroHotel;
	}
    
    
}
