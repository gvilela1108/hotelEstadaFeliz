package br.com.hotelEstadaFeliz.beans;

public class Cliente extends Pessoa {

	private String erroCliente;
	
	public Cliente(){
		super();
	}

	public String getErroCliente() {
		return erroCliente;
	}

	public void setErroCliente(String erroCliente) {
		this.erroCliente = erroCliente;
	}
	
}
