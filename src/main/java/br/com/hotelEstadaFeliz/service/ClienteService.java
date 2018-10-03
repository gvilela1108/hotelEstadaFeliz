package br.com.hotelEstadaFeliz.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotelEstadaFeliz.beans.Cliente;
import br.com.hotelEstadaFeliz.dto.DadosCliente;
import br.com.hotelEstadaFeliz.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente consultarCliente(DadosCliente dadosCliente) throws Exception{
		Cliente retorno = new Cliente();
		
		try {
			Cliente existeCliente = clienteRepository.findByCpf(dadosCliente.getCpf());
			
			if (existeCliente == null) { 
				retorno.setErroCliente("Nenhum Cliente encontrado");
			} else {
				retorno = existeCliente;
			}
		} catch(Exception e) {
			throw e;
		}
		
		return retorno;
	}
	
	public Cliente inserirCliente(DadosCliente dadosCliente) throws Exception{
		Cliente retorno = new Cliente();
		Date dataSistema = new Date();
		String errosRegistrarCliente = "Erro ao cadastrar o cliente";
		boolean erroRegistro = false;
		
		try {
			//Verifica se o cliente já esta registrado
			Cliente existeCliente = clienteRepository.findByCpf(dadosCliente.getCpf());
			
			if (existeCliente == null) { //Cliente não existe
				dadosCliente.setDataCriacao(dataSistema);
				retorno = salvarCliente(dadosCliente);
			} else { //Cliente já existente
				retorno = existeCliente;
			}
			
			//Validações pre retorno
			if (retorno != null) {
				if (retorno.getId().equals("")) {
					erroRegistro = true;
				}
				
				if (retorno.getDataCriacao().before(dataSistema) ) {
					errosRegistrarCliente += " - Cliente já está cadastrado";
					erroRegistro = true;
				}
			} else {
				erroRegistro = true;
			}
			
			if (!erroRegistro) {
				errosRegistrarCliente = "";
			}
			
			retorno.setErroCliente(errosRegistrarCliente);

			
		} catch (Exception e) {
			throw e;
		}

		return retorno;
	}
	
	public Cliente atualizarCliente(DadosCliente dadosCliente) throws Exception{
		Cliente retorno = new Cliente();
		Date dataSistema = new Date();
		String errosAtualizarCliente = "";
		
		try {
			//Verifica se o cliente já esta registrado
			Cliente existeCliente = clienteRepository.findByCpf(dadosCliente.getCpf());
			
			if (existeCliente == null) { //Cliente não existe
				retorno.setErroCliente("Cliente não está cadastrado");
			} else { //Cliente existente
				//Recupera dados chaves para atualização
				dadosCliente.setId(existeCliente.getId());
				dadosCliente.setDataCriacao(existeCliente.getDataCriacao());
				dadosCliente.setDataAlteracao(dataSistema);
				
				retorno = salvarCliente(dadosCliente);
			}
			
			//Validações pre retorno
			if (retorno == null) {
				errosAtualizarCliente = "Erro ao atualizar o cliente";
			}
			
			retorno.setErroCliente(errosAtualizarCliente);

			
		} catch (Exception e) {
			throw e;
		}

		return retorno;
	}
	
	public Cliente deletarCliente(DadosCliente dadosCliente) {
		Cliente retorno = new Cliente();
		try {
			//Verifica se o cliente já esta registrado
			Cliente existeCliente = clienteRepository.findByCpf(dadosCliente.getCpf());
			
			if (existeCliente == null) { //Cliente não existe
				retorno.setErroCliente("Cliente não está cadastrado");
			} else { //Cliente já existente
				clienteRepository.delete(existeCliente);
			}
			
			//Verifica se o cliente foi excluido
			existeCliente = clienteRepository.findByCpf(dadosCliente.getCpf());
			if (existeCliente != null) {
				retorno.setErroCliente("Erro ao excluir o cliente");
			}
			
			return retorno;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Cliente salvarCliente(DadosCliente dadosCliente) {

		Cliente cliente = new Cliente();
		cliente.setId(dadosCliente.getId());
		cliente.setCpf(dadosCliente.getCpf());
		cliente.setNome(dadosCliente.getNome());
		cliente.setEmail(dadosCliente.getEmail());
		cliente.setEndereco(dadosCliente.getEndereco());
		cliente.setTelefone(dadosCliente.getTelefone());
		cliente.setDataCriacao(dadosCliente.getDataCriacao());	
		cliente.setDataAlteracao(dadosCliente.getDataAlteracao());

		
		return clienteRepository.save(cliente);
	}
}
