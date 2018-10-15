package br.com.hotelEstadaFeliz.dto;

import java.util.List;

import br.com.hotelEstadaFeliz.beans.Cliente;

public class RetornoCliente{

	private List<Cliente> cliente;
	private String erroDadosCliente;
	

	public String getErroDadosCliente() {
		return erroDadosCliente;
	}
	public void setErroDadosCliente(String erroDadosCliente) {
		this.erroDadosCliente = erroDadosCliente;
	}
	public List<Cliente> getCliente() {
		return cliente;
	}
	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}

	
}
