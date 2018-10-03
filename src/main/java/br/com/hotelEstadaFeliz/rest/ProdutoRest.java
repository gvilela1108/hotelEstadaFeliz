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

import br.com.hotelEstadaFeliz.beans.Produto;
import br.com.hotelEstadaFeliz.cnt.TipoAcao;
import br.com.hotelEstadaFeliz.dto.DadosProduto;
import br.com.hotelEstadaFeliz.service.ProdutoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/")
public class ProdutoRest {

	@Autowired
	private ProdutoService produtoService;
	
	private static final int consultarProdutoAcao = 0;
	private static final int inserirProdutoAcao = 1;
	private static final int atualizarProdutoAcao = 2;
	private static final int deletarProdutoAcao = 3;
	
	@ApiOperation(
			value="Consultar os dados de determinado produto", 
			notes="Essa operação tem como objetivo consultar os dados especificos de um produto")
	@PostMapping("/consultarProduto")
	private Map<String,Object> consultarProduto(@Valid @RequestBody DadosProduto dadosProduto, Errors errors) throws Exception {	
		return manterDadosProduto(dadosProduto,errors,consultarProdutoAcao);
	}
	
	@ApiOperation(
			value="Cadastrar os dados de determinado produto", 
			notes="Essa operação tem como objetivo cadastrar no sistema os dados especificos de um produto")
	@PostMapping("/inserirProduto")
	private Map<String,Object> inserirProduto(@Valid @RequestBody DadosProduto dadosProduto, Errors errors) throws Exception {
		return manterDadosProduto(dadosProduto,errors,inserirProdutoAcao);
	}
	
	@ApiOperation(
			value="Atualizar os dados de determinado produto", 
			notes="Essa operação tem como objetivo atualizar os dados especificos de um produto")
	@PutMapping("/atualizarProduto")
	private Map<String,Object> atualizarProduto(@Valid @RequestBody DadosProduto dadosProduto, Errors errors) throws Exception {
		return manterDadosProduto(dadosProduto,errors,atualizarProdutoAcao);
	}
	
	@ApiOperation(
			value="Remover os dados de determinado produto", 
			notes="Essa operação tem como objetivo remover os dados especificos de um produto")
	@DeleteMapping("/deletarProduto")
	private Map<String,Object> excluirProduto(@Valid @RequestBody DadosProduto dadosProduto, Errors errors) throws Exception {
		return manterDadosProduto(dadosProduto,errors,deletarProdutoAcao);		
	}
	
	private Map<String,Object> manterDadosProduto(DadosProduto dadosProduto, Errors errors, int idAcao ) throws Exception{
		
		Produto produto = new Produto();
		Map<String, Object> retorno = new HashMap<String, Object>();
		String erroDadosInformadosProduto = "";
		
		if (errors.hasErrors()) {
			erroDadosInformadosProduto = errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
		} else {
			TipoAcao tipoAcao = TipoAcao.getTipoAcaoByCode(idAcao);
			
			switch(tipoAcao) {
				
				case CONSULTAR:
					produto = produtoService.consultarProduto(dadosProduto);
					break;
				case INSERIR:
					produto = produtoService.inserirProduto(dadosProduto);
					break;
				case ATUALIZAR:
					produto = produtoService.atualizarProduto(dadosProduto);
					break;
				case DELETAR:
					produto = produtoService.deletarProduto(dadosProduto);
					break;
				default:
					erroDadosInformadosProduto += "Tipo Ação inválido";
					break;
			}
			
		}
		
		retorno.put("errosDadosInformados", erroDadosInformadosProduto);
		retorno.put("produto", produto);
		
		return retorno;
		
	}
}
