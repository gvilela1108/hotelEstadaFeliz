package br.com.hotelEstadaFeliz.beans;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class Hospedagem {
	private String id;
	@NotBlank(message="Codigo cliente obrigatorio")
	private String idCliente;
	@NotBlank(message="Codigo hotel obrigatorio")
	private String idHotel;
	@NotBlank(message="Codigo quarto obrigatorio")
	private String idQuarto;
	private Consumo consumo;
	private String checkin;
	private String checkout;
	private Date dataCriacao;
	private Date dataAlteracao;
	private String erroHospedagem;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getIdHotel() {
		return idHotel;
	}
	public void setIdHotel(String idHotel) {
		this.idHotel = idHotel;
	}
	public String getIdQuarto() {
		return idQuarto;
	}
	public void setIdQuarto(String idQuarto) {
		this.idQuarto = idQuarto;
	}
	public Consumo getConsumo() {
		return consumo;
	}
	public void setConsumo(Consumo consumo) {
		this.consumo = consumo;
	}
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
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
	public String getErroHospedagem() {
		return erroHospedagem;
	}
	public void setErroHospedagem(String erroHospedagem) {
		this.erroHospedagem = erroHospedagem;
	}
	
}
