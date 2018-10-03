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

import br.com.hotelEstadaFeliz.beans.Cliente;
import br.com.hotelEstadaFeliz.cnt.TipoAcao;
import br.com.hotelEstadaFeliz.dto.DadosCliente;
import br.com.hotelEstadaFeliz.service.ClienteService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/")
public class ClienteRest {

	@Autowired
	private ClienteService clienteService;
	
	private static final int consultarClienteAcao = 0;
	private static final int inserirClienteAcao = 1;
	private static final int atualizarClienteAcao = 2;
	private static final int deletarClienteAcao = 3;
	
	@ApiOperation(
			value="Consultar os dados de determinado cliente", 
			notes="Essa operação tem como objetivo consultar os dados especificos de um cliente")
	@PostMapping("/consultarCliente")
	private Map<String,Object> consultarCliente(@Valid @RequestBody DadosCliente dadosCliente, Errors errors) throws Exception {	
		return manterDadosCliente(dadosCliente,errors,consultarClienteAcao);
	}
	
	@ApiOperation(
			value="Cadastrar os dados de determinado cliente", 
			notes="Essa operação tem como objetivo cadastrar no sistema os dados especificos de um cliente")
	@PostMapping("/inserirCliente")
	private Map<String,Object> inserirCliente(@Valid @RequestBody DadosCliente dadosCliente, Errors errors) throws Exception {
		return manterDadosCliente(dadosCliente,errors,inserirClienteAcao);
	}
	
	@ApiOperation(
			value="Atualizar os dados de determinado cliente", 
			notes="Essa operação tem como objetivo atualizar os dados especificos de um cliente")
	@PutMapping("/atualizarCliente")
	private Map<String,Object> atualizarCliente(@Valid @RequestBody DadosCliente dadosCliente, Errors errors) throws Exception {
		return manterDadosCliente(dadosCliente,errors,atualizarClienteAcao);
	}
	
	@ApiOperation(
			value="Remover os dados de determinado cliente", 
			notes="Essa operação tem como objetivo remover os dados especificos de um cliente")
	@DeleteMapping("/deletarCliente")
	private Map<String,Object> excluirCliente(@Valid @RequestBody DadosCliente dadosCliente, Errors errors) throws Exception {
		return manterDadosCliente(dadosCliente,errors,deletarClienteAcao);		
	}
	
	private Map<String,Object> manterDadosCliente(DadosCliente dadosCliente, Errors errors, int idAcao ) throws Exception{
		
		Cliente cliente = new Cliente();
		Map<String, Object> retorno = new HashMap<String, Object>();
		String erroDadosInformadosCliente = "";
		
		if (errors.hasErrors()) {
			erroDadosInformadosCliente = errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
		} else {
			TipoAcao tipoAcao = TipoAcao.getTipoAcaoByCode(idAcao);
			
			switch(tipoAcao) {
				
				case CONSULTAR:
					cliente = clienteService.consultarCliente(dadosCliente);
					break;
				case INSERIR:
					cliente = clienteService.inserirCliente(dadosCliente);
					break;
				case ATUALIZAR:
					cliente = clienteService.atualizarCliente(dadosCliente);
					break;
				case DELETAR:
					cliente = clienteService.deletarCliente(dadosCliente);
					break;
				default:
					erroDadosInformadosCliente += "Tipo Ação inválido";
					break;
			}
			
		}
		
		retorno.put("errosDadosInformados", erroDadosInformadosCliente);
		retorno.put("cliente", cliente);
		
		return retorno;
		
	}
}
