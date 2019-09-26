package br.com.hotelEstadaFeliz.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/")
public class QuartoRest {

	@Autowired
	private QuartoService quartoService;
	
	private static final int consultarQuartoAcao = 0;
	private static final int inserirQuartoAcao = 1;
	private static final int atualizarQuartoAcao = 2;
	private static final int deletarQuartoAcao = 3;
	private static final int consultarTodos = 4;
	
	@ApiOperation(
			value="Consultar os dados de determinado quarto", 
			notes="Essa operação tem como objetivo consultar os dados especificos de um quarto")
	@PostMapping("/consultarQuarto")
	private List<Quarto> consultarQuarto(@Valid @RequestBody DadosQuarto dadosQuarto, Errors errors) throws Exception {	
		return manterDadosQuarto(dadosQuarto,errors,consultarQuartoAcao);
	}
	
	@ApiOperation(
			value="Cadastrar os dados de determinado quarto", 
			notes="Essa operação tem como objetivo cadastrar no sistema os dados especificos de um quarto")
	@PostMapping("/inserirQuarto")
	private List<Quarto> inserirQuarto(@Valid @RequestBody DadosQuarto dadosQuarto, Errors errors) throws Exception {
		return manterDadosQuarto(dadosQuarto,errors,inserirQuartoAcao);
	}
	
	@ApiOperation(
			value="Atualizar os dados de determinado quarto", 
			notes="Essa operação tem como objetivo atualizar os dados especificos de um quarto")
	@PutMapping("/atualizarQuarto")
	private List<Quarto> atualizarQuarto(@Valid @RequestBody DadosQuarto dadosQuarto, Errors errors) throws Exception {
		return manterDadosQuarto(dadosQuarto,errors,atualizarQuartoAcao);
	}
	
	@ApiOperation(
			value="Remover os dados de determinado quarto", 
			notes="Essa operação tem como objetivo remover os dados especificos de um quarto")
	@DeleteMapping("/deletarQuarto")
	private List<Quarto> excluirQuarto(@Valid @RequestBody DadosQuarto dadosQuarto, Errors errors) throws Exception {
		return manterDadosQuarto(dadosQuarto,errors,deletarQuartoAcao);		
	}
	
	@ApiOperation(
			value="Consultar os dados de todos quartos", 
			notes="Essa operação tem como objetivo consultar os dados especificos de todos os quartos")
	@GetMapping("/consultarTodosQuartos")
	private List<Quarto> consultarTodosQuartos() throws Exception {
		return manterDadosQuarto(null,null,consultarTodos);		
	}
	
	private List<Quarto> manterDadosQuarto(DadosQuarto dadosQuarto, Errors errors, int idAcao ) throws Exception{
		
		Quarto quarto = new Quarto();
		List<Quarto> listaTodos = new ArrayList<Quarto>();
		
		String erroDadosInformadosQuarto = "";
		
		if (idAcao != consultarTodos && errors.hasErrors()) {
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
				case CONSULTAR_TODOS:
					listaTodos = quartoService.consultarTodos();
					break;					
				default:
					erroDadosInformadosQuarto += "Tipo A��o inv�lido";
					break;
			}
			
		}

		List<Quarto> listaQuarto = new ArrayList<Quarto>();

		if(quarto.getId() != null ) {
			listaQuarto.add(quarto);
		} else {
			listaQuarto = listaTodos;
		}
		
		return listaQuarto;
		
	}
}
