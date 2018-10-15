package br.com.hotelEstadaFeliz.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotelEstadaFeliz.beans.Hospedagem;
import br.com.hotelEstadaFeliz.dto.DadosHospedagem;
import br.com.hotelEstadaFeliz.repository.HospedagemRepository;

@Service
public class HospedagemService {

	@Autowired
	private HospedagemRepository hospedagemRepository;
	
	public Hospedagem consultarHospedagem(DadosHospedagem dadosHospedagem) throws Exception{
		Hospedagem retorno = new Hospedagem();
		
		try {
			Hospedagem existeHospedagem = hospedagemRepository.findByIdClienteAndIdQuartoAndIdHotel(dadosHospedagem.getIdCliente(),dadosHospedagem.getIdQuarto(),dadosHospedagem.getIdHotel());
			
			if (existeHospedagem == null) { 
				retorno.setErroHospedagem("Nenhum Hospedagem encontrada");
			} else {
				retorno = existeHospedagem;
			}
		} catch(Exception e) {
			throw e;
		}
		
		return retorno;
	}
	
	public Hospedagem inserirHospedagem(DadosHospedagem dadosHospedagem) throws Exception{
		Hospedagem retorno = new Hospedagem();
		Date dataSistema = new Date();
		String errosRegistrarHospedagem = "Erro ao cadastrar o hospedagem";
		boolean erroRegistro = false;
		
		try {
			//Verifica se o hospedagem j� esta registrado
			Hospedagem existeHospedagem = hospedagemRepository.findByIdClienteAndIdQuartoAndIdHotel(dadosHospedagem.getIdCliente(),dadosHospedagem.getIdQuarto(),dadosHospedagem.getIdHotel());
			
			if (existeHospedagem == null) { //Hospedagem n�o existe
				dadosHospedagem.setDataCriacao(dataSistema);
				retorno = salvarHospedagem(dadosHospedagem);
			} else { //Hospedagem j� existente
				retorno = existeHospedagem;
			}
			
			//Valida��es pre retorno
			if (retorno != null) {
				if (retorno.getId().equals("")) {
					erroRegistro = true;
				}
				
				if (retorno.getDataCriacao().before(dataSistema) ) {
					errosRegistrarHospedagem += " - Hospedagem j� est� cadastrada";
					erroRegistro = true;
				}
			} else {
				erroRegistro = true;
			}
			
			if (!erroRegistro) {
				errosRegistrarHospedagem = "";
			}
			
			retorno.setErroHospedagem(errosRegistrarHospedagem);

			
		} catch (Exception e) {
			throw e;
		}

		return retorno;
	}
	
	public Hospedagem atualizarHospedagem(DadosHospedagem dadosHospedagem) throws Exception{
		Hospedagem retorno = new Hospedagem();
		Date dataSistema = new Date();
		String errosAtualizarHospedagem = "";
		
		try {
			//Verifica se o hospedagem j� esta registrado
			Hospedagem existeHospedagem = hospedagemRepository.findByIdClienteAndIdQuartoAndIdHotel(dadosHospedagem.getIdCliente(),dadosHospedagem.getIdQuarto(),dadosHospedagem.getIdHotel());
			
			if (existeHospedagem == null) { //Hospedagem n�o existe
				retorno.setErroHospedagem("Hospedagem n�o est� cadastrado");
			} else { //Hospedagem existente
				//Recupera dados chaves para atualiza��o
				dadosHospedagem.setId(existeHospedagem.getId());
				dadosHospedagem.setDataCriacao(existeHospedagem.getDataCriacao());
				dadosHospedagem.setDataAlteracao(dataSistema);
				
				retorno = salvarHospedagem(dadosHospedagem);
			}
			
			//Valida��es pre retorno
			if (retorno == null) {
				errosAtualizarHospedagem = "Erro ao atualizar o hospedagem";
			}
			
			retorno.setErroHospedagem(errosAtualizarHospedagem);

			
		} catch (Exception e) {
			throw e;
		}

		return retorno;
	}
	
	public Hospedagem deletarHospedagem(DadosHospedagem dadosHospedagem) {
		Hospedagem retorno = new Hospedagem();
		try {
			//Verifica se o hospedagem j� esta registrado
			Hospedagem existeHospedagem = hospedagemRepository.findByIdClienteAndIdQuartoAndIdHotel(dadosHospedagem.getIdCliente(),dadosHospedagem.getIdQuarto(),dadosHospedagem.getIdHotel());
			
			if (existeHospedagem == null) { //Hospedagem n�o existe
				retorno.setErroHospedagem("Hospedagem n�o est� cadastrado");
			} else { //Hospedagem j� existente
				hospedagemRepository.delete(existeHospedagem);
			}
			
			//Verifica se o hospedagem foi excluido
			existeHospedagem = hospedagemRepository.findByIdClienteAndIdQuartoAndIdHotel(dadosHospedagem.getIdCliente(),dadosHospedagem.getIdQuarto(),dadosHospedagem.getIdHotel());
			if (existeHospedagem != null) {
				retorno.setErroHospedagem("Erro ao excluir o hospedagem");
			}
			
			return retorno;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Hospedagem salvarHospedagem(DadosHospedagem dadosHospedagem) {

		Hospedagem hospedagem = new Hospedagem();
		hospedagem.setId(dadosHospedagem.getId());
		hospedagem.setIdCliente(dadosHospedagem.getIdCliente());
		hospedagem.setIdQuarto(dadosHospedagem.getIdQuarto());
		hospedagem.setIdHotel(dadosHospedagem.getIdHotel());
		hospedagem.setConsumo(dadosHospedagem.getConsumo());
		hospedagem.setCheckin(dadosHospedagem.getCheckin());
		hospedagem.setCheckout(dadosHospedagem.getCheckout());
		hospedagem.setDataCriacao(dadosHospedagem.getDataCriacao());
		hospedagem.setDataAlteracao(dadosHospedagem.getDataAlteracao());
		
		return hospedagemRepository.save(hospedagem);
	}
	
	public List<Hospedagem> consultarTodos() {
		return hospedagemRepository.findAll();
	}
}
