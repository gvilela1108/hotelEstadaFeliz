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
			//Verifica se o hospedagem já esta registrado
			Hospedagem existeHospedagem = hospedagemRepository.findByIdClienteAndIdQuartoAndIdHotel(dadosHospedagem.getIdCliente(),dadosHospedagem.getIdQuarto(),dadosHospedagem.getIdHotel());
			
			if (existeHospedagem == null) { //Hospedagem não existe
				dadosHospedagem.setDataCriacao(dataSistema);
				retorno = salvarHospedagem(dadosHospedagem);
			} else { //Hospedagem já existente
				retorno = existeHospedagem;
			}
			
			//Validações pre retorno
			if (retorno != null) {
				if (retorno.getId().equals("")) {
					erroRegistro = true;
				}
				
				if (retorno.getDataCriacao().before(dataSistema) ) {
					errosRegistrarHospedagem += " - Hospedagem já está cadastrada";
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
			//Verifica se o hospedagem já esta registrado
			Hospedagem existeHospedagem = hospedagemRepository.findByIdClienteAndIdQuartoAndIdHotel(dadosHospedagem.getIdCliente(),dadosHospedagem.getIdQuarto(),dadosHospedagem.getIdHotel());
			
			if (existeHospedagem == null) { //Hospedagem não existe
				retorno.setErroHospedagem("Hospedagem não está cadastrado");
			} else { //Hospedagem existente
				//Recupera dados chaves para atualização
				dadosHospedagem.setId(existeHospedagem.getId());
				dadosHospedagem.setDataCriacao(existeHospedagem.getDataCriacao());
				dadosHospedagem.setDataAlteracao(dataSistema);
				
				retorno = salvarHospedagem(dadosHospedagem);
			}
			
			//Validações pre retorno
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
			//Verifica se o hospedagem já esta registrado
			Hospedagem existeHospedagem = hospedagemRepository.findByIdClienteAndIdQuartoAndIdHotel(dadosHospedagem.getIdCliente(),dadosHospedagem.getIdQuarto(),dadosHospedagem.getIdHotel());
			
			if (existeHospedagem == null) { //Hospedagem não existe
				retorno.setErroHospedagem("Hospedagem não está cadastrado");
			} else { //Hospedagem já existente
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
