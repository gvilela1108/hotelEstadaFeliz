package br.com.hotelEstadaFeliz.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotelEstadaFeliz.beans.Quarto;
import br.com.hotelEstadaFeliz.dto.DadosQuarto;
import br.com.hotelEstadaFeliz.repository.QuartoRepository;

@Service
public class QuartoService {

	@Autowired
	private QuartoRepository quartoRepository;
	
	public Quarto consultarQuarto(DadosQuarto dadosQuarto) throws Exception{
		
		Quarto retorno = new Quarto();
		
		try {
			Quarto existeQuarto = quartoRepository.findByNumeroAndIdHotel(dadosQuarto.getNumero(), dadosQuarto.getIdHotel());
			
			if (existeQuarto == null) { 
				retorno.setErroQuarto("Nenhum Quarto encontrado");
			} else {
				retorno = existeQuarto;
			}
			
		} catch(Exception e) {
			throw e;
		}
		
		return retorno;
	}
	
	public Quarto inserirQuarto(DadosQuarto dadosQuarto) throws Exception{
		Quarto retorno = new Quarto();
		Date dataSistema = new Date();
		String errosRegistrarQuarto = "Erro ao cadastrar o quarto";
		boolean erroRegistro = false;
		
		try {
			//Verifica se o quarto já esta registrado
			Quarto existeQuarto = quartoRepository.findByNumeroAndIdHotel(dadosQuarto.getNumero(), dadosQuarto.getIdHotel());
			
			if (existeQuarto == null) { //Quarto não existe
				dadosQuarto.setDataCriacao(dataSistema);
				retorno = salvarQuarto(dadosQuarto);
			} else { //Quarto já existente
				retorno = existeQuarto;
			}
			
			//Validações pre retorno
			if (retorno != null) {
				if (retorno.getId().equals("")) {
					erroRegistro = true;
				}
				
				if (retorno.getDataCriacao().before(dataSistema) ) {
					errosRegistrarQuarto += " - Quarto já está cadastrado";
					erroRegistro = true;
				}
			} else {
				erroRegistro = true;
			}
			
			if (!erroRegistro) {
				errosRegistrarQuarto = "";
			}
			
			retorno.setErroQuarto(errosRegistrarQuarto);

			
		} catch (Exception e) {
			throw e;
		}

		return retorno;
	}
	
	public Quarto atualizarQuarto(DadosQuarto dadosQuarto) throws Exception{
		Quarto retorno = new Quarto();
		Date dataSistema = new Date();
		String errosAtualizarQuarto = "";
		
		try {
			//Verifica se o quarto já esta registrado
			Quarto existeQuarto = quartoRepository.findByNumeroAndIdHotel(dadosQuarto.getNumero(), dadosQuarto.getIdHotel());
			
			if (existeQuarto == null) { //Quarto não existe
				retorno.setErroQuarto("Quarto não está cadastrado");
			} else { //Quarto existente
				//Recupera dados chaves para atualização
				dadosQuarto.setId(existeQuarto.getId());
				dadosQuarto.setDataCriacao(existeQuarto.getDataCriacao());
				dadosQuarto.setDataAlteracao(dataSistema);
				
				retorno = salvarQuarto(dadosQuarto);
			}
			
			//Validações pre retorno
			if (retorno == null) {
				errosAtualizarQuarto = "Erro ao atualizar o quarto";
			}
			
			retorno.setErroQuarto(errosAtualizarQuarto);

			
		} catch (Exception e) {
			throw e;
		}

		return retorno;
	}
	
	public Quarto deletarQuarto(DadosQuarto dadosQuarto) {
		Quarto retorno = new Quarto();
		try {
			//Verifica se o quarto já esta registrado
			Quarto existeQuarto = quartoRepository.findByNumeroAndIdHotel(dadosQuarto.getNumero(), dadosQuarto.getIdHotel());
			
			if (existeQuarto == null) { //Quarto não existe
				retorno.setErroQuarto("Quarto não está cadastrado");
			} else { //Quarto já existente
				quartoRepository.delete(existeQuarto);
			}
			
			//Verifica se o quarto foi excluido
			existeQuarto = quartoRepository.findByNumeroAndIdHotel(dadosQuarto.getNumero(), dadosQuarto.getIdHotel());
			if (existeQuarto != null) {
				retorno.setErroQuarto("Erro ao excluir o quarto");
			}
			
			return retorno;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Quarto salvarQuarto(DadosQuarto dadosQuarto) {

		Quarto quarto = new Quarto();
		quarto.setId(dadosQuarto.getId());
		quarto.setTipo(dadosQuarto.getTipo());
		quarto.setNumero(dadosQuarto.getNumero());
		quarto.setPreco(dadosQuarto.getPreco());
		quarto.setDescricao(dadosQuarto.getDescricao());
		quarto.setIdHotel(dadosQuarto.getIdHotel());
		quarto.setDataCriacao(dadosQuarto.getDataCriacao());	
		quarto.setDataAlteracao(dadosQuarto.getDataAlteracao());

		return quartoRepository.save(quarto);
	}
}
