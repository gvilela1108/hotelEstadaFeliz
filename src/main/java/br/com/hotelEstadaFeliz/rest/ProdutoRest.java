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

import br.com.hotelEstadaFeliz.beans.Produto;
import br.com.hotelEstadaFeliz.cnt.TipoAcao;
import br.com.hotelEstadaFeliz.dto.DadosProduto;
import br.com.hotelEstadaFeliz.service.ProdutoService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/")
public class ProdutoRest {

	@Autowired
	private ProdutoService produtoService;
	
	private static final int consultarProdutoAcao = 0;
	private static final int inserirProdutoAcao = 1;
	private static final int atualizarProdutoAcao = 2;
	private static final int deletarProdutoAcao = 3;
	private static final int consultarTodos = 4;
	
	@ApiOperation(
			value="Consultar os dados de determinado produto", 
			notes="Essa operação tem como objetivo consultar os dados especificos de um produto")
	@PostMapping("/consultarProduto")
	private List<Produto> consultarProduto(@Valid @RequestBody DadosProduto dadosProduto, Errors errors) throws Exception {	
		return manterDadosProduto(dadosProduto,errors,consultarProdutoAcao);
	}
	
	@ApiOperation(
			value="Cadastrar os dados de determinado produto", 
			notes="Essa operação tem como objetivo cadastrar no sistema os dados especificos de um produto")
	@PostMapping("/inserirProduto")
	private List<Produto> inserirProduto(@Valid @RequestBody DadosProduto dadosProduto, Errors errors) throws Exception {
		return manterDadosProduto(dadosProduto,errors,inserirProdutoAcao);
	}
	
	@ApiOperation(
			value="Atualizar os dados de determinado produto", 
			notes="Essa operação tem como objetivo atualizar os dados especificos de um produto")
	@PutMapping("/atualizarProduto")
	private List<Produto> atualizarProduto(@Valid @RequestBody DadosProduto dadosProduto, Errors errors) throws Exception {
		return manterDadosProduto(dadosProduto,errors,atualizarProdutoAcao);
	}
	
	@ApiOperation(
			value="Remover os dados de determinado produto", 
			notes="Essa operação tem como objetivo remover os dados especificos de um produto")
	@DeleteMapping("/deletarProduto")
	private List<Produto> excluirProduto(@Valid @RequestBody DadosProduto dadosProduto, Errors errors) throws Exception {
		return manterDadosProduto(dadosProduto,errors,deletarProdutoAcao);		
	}
	
	@ApiOperation(
			value="Consultar os dados de todos produtos", 
			notes="Essa operação tem como objetivo consultar os dados especificos de todos os produtos")
	@GetMapping("/consultarTodosProdutos")
	private List<Produto> consultarTodosProdutos() throws Exception {
		return manterDadosProduto(null,null,consultarTodos);		
	}
	
	private List<Produto> manterDadosProduto(DadosProduto dadosProduto, Errors errors, int idAcao ) throws Exception{
		
		Produto produto = new Produto();
		List<Produto> listaTodos = new ArrayList<Produto>();
		
		String erroDadosInformadosProduto = "";
		
		if (idAcao != consultarTodos && errors.hasErrors()) {
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
				case CONSULTAR_TODOS:
					listaTodos = produtoService.consultarTodos();
					break;					
				default:
					erroDadosInformadosProduto += "Tipo Ação inválido";
					break;
			}
			
		}

		List<Produto> listaProduto = new ArrayList<Produto>();

		if(produto.getId() != null ) {
			listaProduto.add(produto);
		} else {
			listaProduto = listaTodos;
		}
		
		return listaProduto;
		
	}
}
