package br.com.hotelEstadaFeliz.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotelEstadaFeliz.beans.Hotel;
import br.com.hotelEstadaFeliz.dto.DadosHotel;
import br.com.hotelEstadaFeliz.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	public Hotel consultarHotel(DadosHotel dadosHotel) throws Exception{
		Hotel retorno = new Hotel();
		
		try {
			Hotel existeHotel = hotelRepository.findByCnpj(dadosHotel.getCnpj());
			
			if (existeHotel == null) { 
				retorno.setErroHotel("Nenhum Hotel encontrado");
			} else {
				retorno = existeHotel;
			}
		} catch(Exception e) {
			throw e;
		}
		
		return retorno;
	}
	
	public Hotel inserirHotel(DadosHotel dadosHotel) throws Exception{
		Hotel retorno = new Hotel();
		Date dataSistema = new Date();
		String errosRegistrarHotel = "Erro ao cadastrar o hotel";
		boolean erroRegistro = false;
		
		try {
			//Verifica se o hotel já esta registrado
			Hotel existeHotel = hotelRepository.findByCnpj(dadosHotel.getCnpj());
			
			if (existeHotel == null) { //Hotel não existe
				dadosHotel.setDataCriacao(dataSistema);
				retorno = salvarHotel(dadosHotel);
			} else { //Hotel já existente
				retorno = existeHotel;
			}
			
			//Validações pre retorno
			if (retorno != null) {
				if (retorno.getId().equals("")) {
					erroRegistro = true;
				}
				
				if (retorno.getDataCriacao().before(dataSistema) ) {
					errosRegistrarHotel += " - Hotel já está cadastrado";
					erroRegistro = true;
				}
			} else {
				erroRegistro = true;
			}
			
			if (!erroRegistro) {
				errosRegistrarHotel = "";
			}
			
			retorno.setErroHotel(errosRegistrarHotel);

			
		} catch (Exception e) {
			throw e;
		}

		return retorno;
	}
	
	public Hotel atualizarHotel(DadosHotel dadosHotel) throws Exception{
		Hotel retorno = new Hotel();
		Date dataSistema = new Date();
		String errosAtualizarHotel = "";
		
		try {
			//Verifica se o hotel já esta registrado
			Hotel existeHotel = hotelRepository.findByCnpj(dadosHotel.getCnpj());
			
			if (existeHotel == null) { //Hotel não existe
				retorno.setErroHotel("Hotel não está cadastrado");
			} else { //Hotel existente
				//Recupera dados chaves para atualização
				dadosHotel.setId(existeHotel.getId());
				dadosHotel.setDataCriacao(existeHotel.getDataCriacao());
				dadosHotel.setDataAlteracao(dataSistema);
				
				retorno = salvarHotel(dadosHotel);
			}
			
			//Validações pre retorno
			if (retorno == null) {
				errosAtualizarHotel = "Erro ao atualizar o hotel";
			}
			
			retorno.setErroHotel(errosAtualizarHotel);

			
		} catch (Exception e) {
			throw e;
		}

		return retorno;
	}
	
	public Hotel deletarHotel(DadosHotel dadosHotel) {
		Hotel retorno = new Hotel();
		try {
			//Verifica se o hotel já esta registrado
			Hotel existeHotel = hotelRepository.findByCnpj(dadosHotel.getCnpj());
			
			if (existeHotel == null) { //Hotel não existe
				retorno.setErroHotel("Hotel não está cadastrado");
			} else { //Hotel já existente
				hotelRepository.delete(existeHotel);
			}
			
			//Verifica se o hotel foi excluido
			existeHotel = hotelRepository.findByCnpj(dadosHotel.getCnpj());
			if (existeHotel != null) {
				retorno.setErroHotel("Erro ao excluir o hotel");
			}
			
			return retorno;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Hotel salvarHotel(DadosHotel dadosHotel) {

		Hotel hotel = new Hotel();
		
		hotel.setId(dadosHotel.getId());
		hotel.setNome(dadosHotel.getNome());
		hotel.setEndereco(dadosHotel.getEndereco());
		hotel.setCnpj(dadosHotel.getCnpj());
		hotel.setTelefone(dadosHotel.getTelefone());
		hotel.setDataCriacao(dadosHotel.getDataCriacao());	
		hotel.setDataAlteracao(dadosHotel.getDataAlteracao());
		return hotelRepository.save(hotel);
	}
	
	public List<Hotel> consultarTodos() {
		return hotelRepository.findAll();
	}
}
