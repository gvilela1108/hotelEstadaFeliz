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

import br.com.hotelEstadaFeliz.beans.Hospedagem;
import br.com.hotelEstadaFeliz.cnt.TipoAcao;
import br.com.hotelEstadaFeliz.dto.DadosHospedagem;
import br.com.hotelEstadaFeliz.service.HospedagemService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/")
public class HospedagemRest {

	@Autowired
	private HospedagemService hospedagemService;
	
	private static final int consultarHospedagemAcao = 0;
	private static final int inserirHospedagemAcao = 1;
	private static final int atualizarHospedagemAcao = 2;
	private static final int deletarHospedagemAcao = 3;
	
	@ApiOperation(
			value="Consultar os dados de determinada hospedagem", 
			notes="Essa operação tem como objetivo consultar os dados especificos de uma hospedagem")
	@PostMapping("/consultarHospedagem")
	private Map<String,Object> consultarHospedagem(@Valid @RequestBody DadosHospedagem dadosHospedagem, Errors errors) throws Exception {	
		return manterDadosHospedagem(dadosHospedagem,errors,consultarHospedagemAcao);
	}
	
	@ApiOperation(
			value="Cadastrar os dados de determinada hospedagem", 
			notes="Essa operação tem como objetivo cadastrar no sistema os dados especificos de uma hospedagem")
	@PostMapping("/inserirHospedagem")
	private Map<String,Object> inserirHospedagem(@Valid @RequestBody DadosHospedagem dadosHospedagem, Errors errors) throws Exception {
		return manterDadosHospedagem(dadosHospedagem,errors,inserirHospedagemAcao);
	}
	
	@ApiOperation(
			value="Atualizar os dados de determinada hospedagem", 
			notes="Essa operação tem como objetivo atualizar os dados especificos de uma hospedagem")
	@PutMapping("/atualizarHospedagem")
	private Map<String,Object> atualizarHospedagem(@Valid @RequestBody DadosHospedagem dadosHospedagem, Errors errors) throws Exception {
		return manterDadosHospedagem(dadosHospedagem,errors,atualizarHospedagemAcao);
	}
	
	@ApiOperation(
			value="Remover os dados de determinada hospedagem", 
			notes="Essa operação tem como objetivo remover os dados especificos de uma hospedagem")
	@DeleteMapping("/deletarHospedagem")
	private Map<String,Object> excluirHospedagem(@Valid @RequestBody DadosHospedagem dadosHospedagem, Errors errors) throws Exception {
		return manterDadosHospedagem(dadosHospedagem,errors,deletarHospedagemAcao);		
	}
	
	private Map<String,Object> manterDadosHospedagem(DadosHospedagem dadosHospedagem, Errors errors, int idAcao ) throws Exception{
		
		Hospedagem hospedagem = new Hospedagem();
		Map<String, Object> retorno = new HashMap<String, Object>();
		String erroDadosInformadosHospedagem = "";
		
		if (errors.hasErrors()) {
			erroDadosInformadosHospedagem = errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
		} else {
			TipoAcao tipoAcao = TipoAcao.getTipoAcaoByCode(idAcao);
			
			switch(tipoAcao) {
				
				case CONSULTAR:
					hospedagem = hospedagemService.consultarHospedagem(dadosHospedagem);
					break;
				case INSERIR:
					hospedagem = hospedagemService.inserirHospedagem(dadosHospedagem);
					break;
				case ATUALIZAR:
					hospedagem = hospedagemService.atualizarHospedagem(dadosHospedagem);
					break;
				case DELETAR:
					hospedagem = hospedagemService.deletarHospedagem(dadosHospedagem);
					break;
				default:
					erroDadosInformadosHospedagem += "Tipo Ação inválido";
					break;
			}
			
		}
		
		retorno.put("errosDadosInformados", erroDadosInformadosHospedagem);
		retorno.put("hospedagem", hospedagem);
		
		return retorno;
		
	}
}
