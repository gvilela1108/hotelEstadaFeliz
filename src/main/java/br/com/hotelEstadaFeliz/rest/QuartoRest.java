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

import br.com.hotelEstadaFeliz.beans.Quarto;
import br.com.hotelEstadaFeliz.cnt.TipoAcao;
import br.com.hotelEstadaFeliz.dto.DadosQuarto;
import br.com.hotelEstadaFeliz.service.QuartoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/")
public class QuartoRest {

	@Autowired
	private QuartoService quartoService;
	
	private static final int consultarQuartoAcao = 0;
	private static final int inserirQuartoAcao = 1;
	private static final int atualizarQuartoAcao = 2;
	private static final int deletarQuartoAcao = 3;
	
	@ApiOperation(
			value="Consultar os dados de determinado quarto", 
			notes="Essa operação tem como objetivo consultar os dados especificos de um quarto")
	@PostMapping("/consultarQuarto")
	private Map<String,Object> consultarQuarto(@Valid @RequestBody DadosQuarto dadosQuarto, Errors errors) throws Exception {	
		return manterDadosQuarto(dadosQuarto,errors,consultarQuartoAcao);
	}
	
	@ApiOperation(
			value="Cadastrar os dados de determinado quarto", 
			notes="Essa operação tem como objetivo cadastrar no sistema os dados especificos de um quarto")
	@PostMapping("/inserirQuarto")
	private Map<String,Object> inserirQuarto(@Valid @RequestBody DadosQuarto dadosQuarto, Errors errors) throws Exception {
		return manterDadosQuarto(dadosQuarto,errors,inserirQuartoAcao);
	}
	
	@ApiOperation(
			value="Atualizar os dados de determinado quarto", 
			notes="Essa operação tem como objetivo atualizar os dados especificos de um quarto")
	@PutMapping("/atualizarQuarto")
	private Map<String,Object> atualizarQuarto(@Valid @RequestBody DadosQuarto dadosQuarto, Errors errors) throws Exception {
		return manterDadosQuarto(dadosQuarto,errors,atualizarQuartoAcao);
	}
	
	@ApiOperation(
			value="Remover os dados de determinado quarto", 
			notes="Essa operação tem como objetivo remover os dados especificos de um quarto")
	@DeleteMapping("/deletarQuarto")
	private Map<String,Object> excluirQuarto(@Valid @RequestBody DadosQuarto dadosQuarto, Errors errors) throws Exception {
		return manterDadosQuarto(dadosQuarto,errors,deletarQuartoAcao);		
	}
	
	private Map<String,Object> manterDadosQuarto(DadosQuarto dadosQuarto, Errors errors, int idAcao ) throws Exception{
		
		Quarto quarto = new Quarto();
		Map<String, Object> retorno = new HashMap<String, Object>();
		String erroDadosInformadosQuarto = "";
		
		if (errors.hasErrors()) {
			erroDadosInformadosQuarto = errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
		} else {
			TipoAcao tipoAcao = TipoAcao.getTipoAcaoByCode(idAcao);
			
			switch(tipoAcao) {
				
				case CONSULTAR:
					quarto = quartoService.consultarQuarto(dadosQuarto);
					break;
				case INSERIR:
					quarto = quartoService.inserirQuarto(dadosQuarto);
					break;
				case ATUALIZAR:
					quarto = quartoService.atualizarQuarto(dadosQuarto);
					break;
				case DELETAR:
					quarto = quartoService.deletarQuarto(dadosQuarto);
					break;
				default:
					erroDadosInformadosQuarto += "Tipo Ação inválido";
					break;
			}
			
		}
		
		retorno.put("errosDadosInformados", erroDadosInformadosQuarto);
		retorno.put("quarto", quarto);
		
		return retorno;
		
	}
}
