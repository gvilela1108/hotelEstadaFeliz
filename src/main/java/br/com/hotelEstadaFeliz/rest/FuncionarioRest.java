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

import br.com.hotelEstadaFeliz.beans.Funcionario;
import br.com.hotelEstadaFeliz.cnt.TipoAcao;
import br.com.hotelEstadaFeliz.dto.DadosFuncionario;
import br.com.hotelEstadaFeliz.service.FuncionarioService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/")
public class FuncionarioRest {

	@Autowired
	private FuncionarioService funcionarioService;
	
	private static final int consultarFuncionarioAcao = 0;
	private static final int inserirFuncionarioAcao = 1;
	private static final int atualizarFuncionarioAcao = 2;
	private static final int deletarFuncionarioAcao = 3;
	private static final int consultarTodos = 4;
	
	@ApiOperation(
			value="Consultar os dados de determinado funcionario", 
			notes="Essa operação tem como objetivo consultar os dados especificos de um funcionario")
	@PostMapping("/consultarFuncionario")
	private List<Funcionario> consultarFuncionario(@Valid @RequestBody DadosFuncionario dadosFuncionario, Errors errors) throws Exception {	
		return manterDadosFuncionario(dadosFuncionario,errors,consultarFuncionarioAcao);
	}
	
	@ApiOperation(
			value="Cadastrar os dados de determinado funcionario", 
			notes="Essa operação tem como objetivo cadastrar no sistema os dados especificos de um funcionario")
	@PostMapping("/inserirFuncionario")
	private List<Funcionario> inserirFuncionario(@Valid @RequestBody DadosFuncionario dadosFuncionario, Errors errors) throws Exception {
		return manterDadosFuncionario(dadosFuncionario,errors,inserirFuncionarioAcao);
	}
	
	@ApiOperation(
			value="Atualizar os dados de determinado funcionario", 
			notes="Essa operação tem como objetivo atualizar os dados especificos de um funcionario")
	@PutMapping("/atualizarFuncionario")
	private List<Funcionario> atualizarFuncionario(@Valid @RequestBody DadosFuncionario dadosFuncionario, Errors errors) throws Exception {
		return manterDadosFuncionario(dadosFuncionario,errors,atualizarFuncionarioAcao);
	}
	
	@ApiOperation(
			value="Remover os dados de determinado funcionario", 
			notes="Essa operação tem como objetivo remover os dados especificos de um funcionario")
	@DeleteMapping("/deletarFuncionario")
	private List<Funcionario> excluirFuncionario(@Valid @RequestBody DadosFuncionario dadosFuncionario, Errors errors) throws Exception {
		return manterDadosFuncionario(dadosFuncionario,errors,deletarFuncionarioAcao);		
	}
	
	@ApiOperation(
			value="Consultar os dados de todos funcionarios", 
			notes="Essa operação tem como objetivo consultar os dados especificos de todos os funcionarios")
	@GetMapping("/consultarTodosFuncionarios")
	private List<Funcionario> consultarTodosFuncionarios() throws Exception {
		return manterDadosFuncionario(null,null,consultarTodos);		
	}
	
	private List<Funcionario> manterDadosFuncionario(DadosFuncionario dadosFuncionario, Errors errors, int idAcao ) throws Exception{
		
		Funcionario funcionario = new Funcionario();
		List<Funcionario> listaTodos = new ArrayList<Funcionario>();
		
		String erroDadosInformadosFuncionario = "";
		
		if (idAcao != consultarTodos && errors.hasErrors()) {
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
				case CONSULTAR_TODOS:
					listaTodos = funcionarioService.consultarTodos();
					break;					
				default:
					erroDadosInformadosFuncionario += "Tipo A��o inv�lido";
					break;
			}
			
		}

		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();

		if(funcionario.getCpf() != null ) {
			listaFuncionario.add(funcionario);
		} else {
			listaFuncionario = listaTodos;
		}
		
		return listaFuncionario;
		
	}
}
