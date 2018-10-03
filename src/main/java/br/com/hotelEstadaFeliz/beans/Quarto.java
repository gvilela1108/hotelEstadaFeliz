package br.com.hotelEstadaFeliz.beans;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class Quarto {
	
    private String id;
    private String tipo;
    @NotNull(message="Número do quarto obrigatório")
    private String numero;
    private Float preco;
    private String descricao;
    @NotNull(message="Id Hotel obrigatório")
    private String idHotel;
    private Date dataCriacao;
    private Date dataAlteracao;
    private String erroQuarto;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Float getPreco() {
		return preco;
	}
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getIdHotel() {
		return idHotel;
	}
	public void setIdHotel(String idHotel) {
		this.idHotel = idHotel;
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
	public String getErroQuarto() {
		return erroQuarto;
	}
	public void setErroQuarto(String erroQuarto) {
		this.erroQuarto = erroQuarto;
	}
    
}
