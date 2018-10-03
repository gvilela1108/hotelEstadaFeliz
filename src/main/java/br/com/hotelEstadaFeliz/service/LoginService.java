package br.com.hotelEstadaFeliz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotelEstadaFeliz.beans.Funcionario;
import br.com.hotelEstadaFeliz.dto.DadosLogin;
import br.com.hotelEstadaFeliz.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	public Funcionario executarLogin(DadosLogin dadosLogin) throws Exception{
		Funcionario retorno = new Funcionario();
		
		try {
			retorno = loginRepository.findByEmailAndSenha(dadosLogin.getEmail(), dadosLogin.getSenha());
		} catch (Exception e) {
			throw e;
		}
		
		return retorno;
	}
	 
}
