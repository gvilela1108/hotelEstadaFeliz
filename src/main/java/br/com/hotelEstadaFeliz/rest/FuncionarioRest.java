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

import br.com.hotelEstadaFeliz.beans.Funcionario;
import br.com.hotelEstadaFeliz.cnt.TipoAcao;
import br.com.hotelEstadaFeliz.dto.DadosFuncionario;
import br.com.hotelEstadaFeliz.service.FuncionarioService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/")
public class FuncionarioRest {

	@Autowired
	private FuncionarioService funcionarioService;
	
	private static final int consultarFuncionarioAcao = 0;
	private static final int inserirFuncionarioAcao = 1;
	private static final int atualizarFuncionarioAcao = 2;
	private static final int deletarFuncionarioAcao = 3;
	
	@ApiOperation(
			value="Consultar os dados de determinado funcionario", 
			notes="Essa operação tem como objetivo consultar os dados especificos de um funcionario")
	@PostMapping("/consultarFuncionario")
	private Map<String,Object> consultarFuncionario(@Valid @RequestBody DadosFuncionario dadosFuncionario, Errors errors) throws Exception {	
		return manterDadosFuncionario(dadosFuncionario,errors,consultarFuncionarioAcao);
	}
	
	@ApiOperation(
			value="Cadastrar os dados de determinado funcionario", 
			notes="Essa operação tem como objetivo cadastrar no sistema os dados especificos de um funcionario")
	@PostMapping("/inserirFuncionario")
	private Map<String,Object> inserirFuncionario(@Valid @RequestBody DadosFuncionario dadosFuncionario, Errors errors) throws Exception {
		return manterDadosFuncionario(dadosFuncionario,errors,inserirFuncionarioAcao);
	}
	
	@ApiOperation(
			value="Atualizar os dados de determinado funcionario", 
			notes="Essa operação tem como objetivo atualizar os dados especificos de um funcionario")
	@PutMapping("/atualizarFuncionario")
	private Map<String,Object> atualizarFuncionario(@Valid @RequestBody DadosFuncionario dadosFuncionario, Errors errors) throws Exception {
		return manterDadosFuncionario(dadosFuncionario,errors,atualizarFuncionarioAcao);
	}
	
	@ApiOperation(
			value="Remover os dados de determinado funcionario", 
			notes="Essa operação tem como objetivo remover os dados especificos de um funcionario")
	@DeleteMapping("/deletarFuncionario")
	private Map<String,Object> excluirFuncionario(@Valid @RequestBody DadosFuncionario dadosFuncionario, Errors errors) throws Exception {
		return manterDadosFuncionario(dadosFuncionario,errors,deletarFuncionarioAcao);		
	}
	
	private Map<String,Object> manterDadosFuncionario(DadosFuncionario dadosFuncionario, Errors errors, int idAcao ) throws Exception{
		
		Funcionario funcionario = new Funcionario();
		Map<String, Object> retorno = new HashMap<String, Object>();
		String erroDadosInformadosFuncionario = "";
		
		if (errors.hasErrors()) {
			erroDadosInformadosFuncionario = errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
		} else {
			TipoAcao tipoAcao = TipoAcao.getTipoAcaoByCode(idAcao);
			
			switch(tipoAcao) {
				
				case CONSULTAR:
					funcionario = funcionarioService.consultarFuncionario(dadosFuncionario);
					break;
				case INSERIR:
					funcionario = funcionarioService.inserirFuncionario(dadosFuncionario);
					break;
				case ATUALIZAR:
					funcionario = funcionarioService.atualizarFuncionario(dadosFuncionario);
					break;
				case DELETAR:
					funcionario = funcionarioService.deletarFuncionario(dadosFuncionario);
					break;
				default:
					erroDadosInformadosFuncionario += "Tipo Ação inválido";
					break;
			}
			
		}
		
		retorno.put("errosDadosInformados", erroDadosInformadosFuncionario);
		retorno.put("funcionario", funcionario);
		
		return retorno;
		
	}
}
