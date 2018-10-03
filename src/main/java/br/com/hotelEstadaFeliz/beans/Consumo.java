package br.com.hotelEstadaFeliz.beans;

public class Consumo {

	protected String idProduto;
	protected String nomeProduto;
	protected Long quantidadeConsumida;
	protected Double precoTotal;
	protected String dataConsumo;
	
	public String getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Long getQuantidadeConsumida() {
		return quantidadeConsumida;
	}
	public void setQuantidadeConsumida(Long quantidadeConsumida) {
		this.quantidadeConsumida = quantidadeConsumida;
	}
	public Double getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}
	public String getDataConsumo() {
		return dataConsumo;
	}
	public void setDataConsumo(String dataConsumo) {
		this.dataConsumo = dataConsumo;
	}
	
	
}
