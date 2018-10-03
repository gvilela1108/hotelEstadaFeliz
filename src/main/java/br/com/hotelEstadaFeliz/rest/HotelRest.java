package br.com.hotelEstadaFeliz.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotelEstadaFeliz.beans.Hotel;
import br.com.hotelEstadaFeliz.cnt.TipoAcao;
import br.com.hotelEstadaFeliz.dto.DadosHotel;
import br.com.hotelEstadaFeliz.service.HotelService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/")
public class HotelRest {

	@Autowired
	private HotelService hotelService;
	
	private static final int consultarHotelAcao = 0;
	private static final int inserirHotelAcao = 1;
	private static final int atualizarHotelAcao = 2;
	private static final int deletarHotelAcao = 3;
	
	@ApiOperation(
			value="Consultar os dados de determinado hotel", 
			notes="Essa operação tem como objetivo consultar os dados especificos de um hotel")
	@PostMapping("/consultarHotel")
	private Map<String,Object> consultarHotel(@Valid @RequestBody DadosHotel dadosHotel, Errors errors) throws Exception {	
		return manterDadosHotel(dadosHotel,errors,consultarHotelAcao);
	}
	
	@ApiOperation(
			value="Cadastrar os dados de determinado hotel", 
			notes="Essa operação tem como objetivo cadastrar no sistema os dados especificos de um hotel")
	@PostMapping("/inserirHotel")
	private Map<String,Object> inserirHotel(@Valid @RequestBody DadosHotel dadosHotel, Errors errors) throws Exception {
		return manterDadosHotel(dadosHotel,errors,inserirHotelAcao);
	}
	
	@ApiOperation(
			value="Atualizar os dados de determinado hotel", 
			notes="Essa operação tem como objetivo atualizar os dados especificos de um hotel")
	@PutMapping("/atualizarHotel")
	private Map<String,Object> atualizarHotel(@Valid @RequestBody DadosHotel dadosHotel, Errors errors) throws Exception {
		return manterDadosHotel(dadosHotel,errors,atualizarHotelAcao);
	}
	
	@ApiOperation(
			value="Remover os dados de determinado hotel", 
			notes="Essa operação tem como objetivo remover os dados especificos de um hotel")
	@DeleteMapping("/deletarHotel")
	private Map<String,Object> excluirHotel(@Valid @RequestBody DadosHotel dadosHotel, Errors errors) throws Exception {
		return manterDadosHotel(dadosHotel,errors,deletarHotelAcao);		
	}
	
	private Map<String,Object> manterDadosHotel(DadosHotel dadosHotel, Errors errors, int idAcao ) throws Exception{
		
		Hotel hotel = new Hotel();
		Map<String, Object> retorno = new HashMap<String, Object>();
		String erroDadosInformadosHotel = "";
		
		if (errors.hasErrors()) {
			erroDadosInformadosHotel = errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
		} else {
			TipoAcao tipoAcao = TipoAcao.getTipoAcaoByCode(idAcao);
			
			switch(tipoAcao) {
				
				case CONSULTAR:
					hotel = hotelService.consultarHotel(dadosHotel);
					break;
				case INSERIR:
					hotel = hotelService.inserirHotel(dadosHotel);
					break;
				case ATUALIZAR:
					hotel = hotelService.atualizarHotel(dadosHotel);
					break;
				case DELETAR:
					hotel = hotelService.deletarHotel(dadosHotel);
					break;
				default:
					erroDadosInformadosHotel += "Tipo Ação inválido";
					break;
			}
			
		}
		
		retorno.put("errosDadosInformados", erroDadosInformadosHotel);
		retorno.put("hotel", hotel);
		
		return retorno;
		
	}
}
