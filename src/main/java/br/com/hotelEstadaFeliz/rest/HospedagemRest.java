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

import br.com.hotelEstadaFeliz.beans.Hospedagem;
import br.com.hotelEstadaFeliz.cnt.TipoAcao;
import br.com.hotelEstadaFeliz.dto.DadosHospedagem;
import br.com.hotelEstadaFeliz.service.HospedagemService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/")
public class HospedagemRest {

	@Autowired
	private HospedagemService hospedagemService;
	
	private static final int consultarHospedagemAcao = 0;
	private static final int inserirHospedagemAcao = 1;
	private static final int atualizarHospedagemAcao = 2;
	private static final int deletarHospedagemAcao = 3;
	private static final int consultarTodos = 4;
	
	@ApiOperation(
			value="Consultar os dados de determinado hospedagem", 
			notes="Essa operação tem como objetivo consultar os dados especificos de um hospedagem")
	@PostMapping("/consultarHospedagem")
	private List<Hospedagem> consultarHospedagem(@Valid @RequestBody DadosHospedagem dadosHospedagem, Errors errors) throws Exception {	
		return manterDadosHospedagem(dadosHospedagem,errors,consultarHospedagemAcao);
	}
	
	@ApiOperation(
			value="Cadastrar os dados de determinado hospedagem", 
			notes="Essa operação tem como objetivo cadastrar no sistema os dados especificos de um hospedagem")
	@PostMapping("/inserirHospedagem")
	private List<Hospedagem> inserirHospedagem(@Valid @RequestBody DadosHospedagem dadosHospedagem, Errors errors) throws Exception {
		return manterDadosHospedagem(dadosHospedagem,errors,inserirHospedagemAcao);
	}
	
	@ApiOperation(
			value="Atualizar os dados de determinado hospedagem", 
			notes="Essa operação tem como objetivo atualizar os dados especificos de um hospedagem")
	@PutMapping("/atualizarHospedagem")
	private List<Hospedagem> atualizarHospedagem(@Valid @RequestBody DadosHospedagem dadosHospedagem, Errors errors) throws Exception {
		return manterDadosHospedagem(dadosHospedagem,errors,atualizarHospedagemAcao);
	}
	
	@ApiOperation(
			value="Remover os dados de determinado hospedagem", 
			notes="Essa operação tem como objetivo remover os dados especificos de um hospedagem")
	@DeleteMapping("/deletarHospedagem")
	private List<Hospedagem> excluirHospedagem(@Valid @RequestBody DadosHospedagem dadosHospedagem, Errors errors) throws Exception {
		return manterDadosHospedagem(dadosHospedagem,errors,deletarHospedagemAcao);		
	}
	
	@ApiOperation(
			value="Consultar os dados de todas hospedagens", 
			notes="Essa operação tem como objetivo consultar os dados especificos de todos as hospedagens")
	@GetMapping("/consultarTodasHospedagens")
	private List<Hospedagem> consultarTodosHospedagems() throws Exception {
		return manterDadosHospedagem(null,null,consultarTodos);		
	}
	
	private List<Hospedagem> manterDadosHospedagem(DadosHospedagem dadosHospedagem, Errors errors, int idAcao ) throws Exception{
		
		Hospedagem hospedagem = new Hospedagem();
		List<Hospedagem> listaTodos = new ArrayList<Hospedagem>();
		
		String erroDadosInformadosHospedagem = "";
		
		if (idAcao != consultarTodos && errors.hasErrors()) {
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
				case CONSULTAR_TODOS:
					listaTodos = hospedagemService.consultarTodos();
					break;					
				default:
					erroDadosInformadosHospedagem += "Tipo A��o inv�lido";
					break;
			}
			
		}

		List<Hospedagem> listaHospedagem = new ArrayList<Hospedagem>();

		if(hospedagem.getId() != null ) {
			listaHospedagem.add(hospedagem);
		} else {
			listaHospedagem = listaTodos;
		}
		
		return listaHospedagem;
		
	}
}
