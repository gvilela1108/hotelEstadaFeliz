package br.com.hotelEstadaFeliz.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotelEstadaFeliz.beans.Funcionario;
import br.com.hotelEstadaFeliz.dto.DadosFuncionario;
import br.com.hotelEstadaFeliz.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public Funcionario consultarFuncionario(DadosFuncionario dadosFuncionario) throws Exception{
		Funcionario retorno = new Funcionario();
		
		try {
			Funcionario existeFuncionario = funcionarioRepository.findByCpf(dadosFuncionario.getCpf());
			
			if (existeFuncionario == null) { 
				retorno.setErroFuncionario("Nenhum Funcionario encontrado");
			} else {
				retorno = existeFuncionario;
			}
		} catch(Exception e) {
			throw e;
		}
		
		return retorno;
	}
	
	public Funcionario inserirFuncionario(DadosFuncionario dadosFuncionario) throws Exception{
		Funcionario retorno = new Funcionario();
		Date dataSistema = new Date();
		String errosRegistrarFuncionario = "Erro ao cadastrar o funcionario";
		boolean erroRegistro = false;
		
		try {
			//Verifica se o funcionario já esta registrado
			Funcionario existeFuncionario = funcionarioRepository.findByCpf(dadosFuncionario.getCpf());
			
			if (existeFuncionario == null) { //Funcionario não existe
				dadosFuncionario.setDataCriacao(dataSistema);
				retorno = salvarFuncionario(dadosFuncionario);
			} else { //Funcionario já existente
				retorno = existeFuncionario;
			}
			
			//Validações pre retorno
			if (retorno != null) {
				if (retorno.getId().equals("")) {
					erroRegistro = true;
				}
				
				if (retorno.getDataCriacao().before(dataSistema) ) {
					errosRegistrarFuncionario += " - Funcionario já está cadastrado";
					erroRegistro = true;
				}
			} else {
				erroRegistro = true;
			}
			
			if (!erroRegistro) {
				errosRegistrarFuncionario = "";
			}
			
			retorno.setErroFuncionario(errosRegistrarFuncionario);

			
		} catch (Exception e) {
			throw e;
		}

		return retorno;
	}
	
	public Funcionario atualizarFuncionario(DadosFuncionario dadosFuncionario) throws Exception{
		Funcionario retorno = new Funcionario();
		Date dataSistema = new Date();
		String errosAtualizarFuncionario = "";
		
		try {
			//Verifica se o funcionario já esta registrado
			Funcionario existeFuncionario = funcionarioRepository.findByCpf(dadosFuncionario.getCpf());
			
			if (existeFuncionario == null) { //Funcionario não existe
				retorno.setErroFuncionario("Funcionario não está cadastrado");
			} else { //Funcionario existente
				//Recupera dados chaves para atualização
				dadosFuncionario.setId(existeFuncionario.getId());
				dadosFuncionario.setDataCriacao(existeFuncionario.getDataCriacao());
				dadosFuncionario.setDataAlteracao(dataSistema);
				
				retorno = salvarFuncionario(dadosFuncionario);
			}
			
			//Validações pre retorno
			if (retorno == null) {
				errosAtualizarFuncionario = "Erro ao atualizar o funcionario";
			}
			
			retorno.setErroFuncionario(errosAtualizarFuncionario);

			
		} catch (Exception e) {
			throw e;
		}

		return retorno;
	}
	
	public Funcionario deletarFuncionario(DadosFuncionario dadosFuncionario) {
		Funcionario retorno = new Funcionario();
		try {
			//Verifica se o funcionario já esta registrado
			Funcionario existeFuncionario = funcionarioRepository.findByCpf(dadosFuncionario.getCpf());
			
			if (existeFuncionario == null) { //Funcionario não existe
				retorno.setErroFuncionario("Funcionario não está cadastrado");
			} else { //Funcionario já existente
				funcionarioRepository.delete(existeFuncionario);
			}
			
			//Verifica se o funcionario foi excluido
			existeFuncionario = funcionarioRepository.findByCpf(dadosFuncionario.getCpf());
			if (existeFuncionario != null) {
				retorno.setErroFuncionario("Erro ao excluir o funcionario");
			}
			
			return retorno;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Funcionario salvarFuncionario(DadosFuncionario dadosFuncionario) {

		Funcionario funcionario = new Funcionario();
		funcionario.setId(dadosFuncionario.getId());
		funcionario.setCpf(dadosFuncionario.getCpf());
		funcionario.setNome(dadosFuncionario.getNome());
		funcionario.setEmail(dadosFuncionario.getEmail());
		funcionario.setEndereco(dadosFuncionario.getEndereco());
		funcionario.setTelefone(dadosFuncionario.getTelefone());
		funcionario.setDataCriacao(dadosFuncionario.getDataCriacao());	
		funcionario.setDataAlteracao(dadosFuncionario.getDataAlteracao());
		funcionario.setSalario(dadosFuncionario.getSalario());
		funcionario.setTipoFuncionario(dadosFuncionario.getTipoFuncionario());
		funcionario.setSenha(dadosFuncionario.getSenha());
		
		return funcionarioRepository.save(funcionario);
	}
	
	
	public List<Funcionario> consultarTodos() {
		return funcionarioRepository.findAll();
	}
}
