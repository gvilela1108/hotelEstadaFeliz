package br.com.hotelEstadaFeliz.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotelEstadaFeliz.beans.Produto;
import br.com.hotelEstadaFeliz.dto.DadosProduto;
import br.com.hotelEstadaFeliz.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto consultarProduto(DadosProduto dadosProduto) throws Exception{
		Produto retorno = new Produto();
		
		try {
			Produto existeProduto = produtoRepository.findByNome(dadosProduto.getNome());
			
			if (existeProduto == null) { 
				retorno.setErroProduto("Nenhum Produto encontrado");
			} else {
				retorno = existeProduto;
			}
		} catch(Exception e) {
			throw e;
		}
		
		return retorno;
	}
	
	public Produto inserirProduto(DadosProduto dadosProduto) throws Exception{
		Produto retorno = new Produto();
		Date dataSistema = new Date();
		String errosRegistrarProduto = "Erro ao cadastrar o produto";
		boolean erroRegistro = false;
		
		try {
			//Verifica se o produto já esta registrado
			Produto existeProduto = produtoRepository.findByNome(dadosProduto.getNome());
			
			if (existeProduto == null) { //Produto não existe
				dadosProduto.setDataCriacao(dataSistema);
				retorno = salvarProduto(dadosProduto);
			} else { //Produto já existente
				retorno = existeProduto;
			}
			
			//Validações pre retorno
			if (retorno != null) {
				if (retorno.getId().equals("")) {
					erroRegistro = true;
				}
				
				if (retorno.getDataCriacao().before(dataSistema) ) {
					errosRegistrarProduto += " - Produto já está cadastrado";
					erroRegistro = true;
				}
			} else {
				erroRegistro = true;
			}
			
			if (!erroRegistro) {
				errosRegistrarProduto = "";
			}
			
			retorno.setErroProduto(errosRegistrarProduto);

			
		} catch (Exception e) {
			throw e;
		}

		return retorno;
	}
	
	public Produto atualizarProduto(DadosProduto dadosProduto) throws Exception{
		Produto retorno = new Produto();
		Date dataSistema = new Date();
		String errosAtualizarProduto = "";
		
		try {
			//Verifica se o produto já esta registrado
			Produto existeProduto = produtoRepository.findByNome(dadosProduto.getNome());
			
			if (existeProduto == null) { //Produto não existe
				retorno.setErroProduto("Produto não está cadastrado");
			} else { //Produto existente
				//Recupera dados chaves para atualização
				dadosProduto.setId(existeProduto.getId());
				dadosProduto.setDataCriacao(existeProduto.getDataCriacao());
				dadosProduto.setDataAlteracao(dataSistema);
				
				retorno = salvarProduto(dadosProduto);
			}
			
			//Validações pre retorno
			if (retorno == null) {
				errosAtualizarProduto = "Erro ao atualizar o produto";
			}
			
			retorno.setErroProduto(errosAtualizarProduto);

			
		} catch (Exception e) {
			throw e;
		}

		return retorno;
	}
	
	public Produto deletarProduto(DadosProduto dadosProduto) {
		Produto retorno = new Produto();
		try {
			//Verifica se o produto já esta registrado
			Produto existeProduto = produtoRepository.findByNome(dadosProduto.getNome());
			
			if (existeProduto == null) { //Produto não existe
				retorno.setErroProduto("Produto não está cadastrado");
			} else { //Produto já existente
				produtoRepository.delete(existeProduto);
			}
			
			//Verifica se o produto foi excluido
			existeProduto = produtoRepository.findByNome(dadosProduto.getNome());
			if (existeProduto != null) {
				retorno.setErroProduto("Erro ao excluir o produto");
			}
			
			return retorno;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Produto salvarProduto(DadosProduto dadosProduto) {

		Produto produto = new Produto();
		produto.setId(dadosProduto.getId());
		produto.setNome(dadosProduto.getNome());
		produto.setDescricao(dadosProduto.getDescricao());
		produto.setPreco(dadosProduto.getPreco());
		produto.setQuantidade(dadosProduto.getQuantidade());
		

		
		return produtoRepository.save(produto);
	}
}
