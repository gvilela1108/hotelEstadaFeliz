package br.com.hotelEstadaFeliz.beans;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Produto {
	
	private String id;
	@NotBlank(message="Nome do produto obrigatório")
	private String nome;
	@NotBlank(message="Preço do produto obrigatório")
	private Float preco;
	private String descricao;
	@NotBlank(message="Status do produto obrigatório")
	private String status;
	@NotNull(message="Quantidade obrigatória")
	private Long quantidade;
	private String erroProduto;
	private Date dataCriacao;
	private Date dataAlteracao;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErroProduto() {
		return erroProduto;
	}
	public void setErroProduto(String erroProduto) {
		this.erroProduto = erroProduto;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
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
	
}
