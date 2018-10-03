package br.com.hotelEstadaFeliz.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotelEstadaFeliz.beans.Funcionario;
import br.com.hotelEstadaFeliz.dto.DadosLogin;
import br.com.hotelEstadaFeliz.service.LoginService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/")
public class LoginRest {

	@Autowired
	private LoginService loginService;
	
	@ApiOperation(
			value="Realizar o login do sistema", 
			notes="Essa operação tem como objetivo acessar o sistema e retornar o devido tipo de acesso do funcionário")
	@PostMapping("/login")
	private Map<String, String> login(@Valid @RequestBody DadosLogin dadosLogin, Errors errors) throws Exception {
		
		String errosLogin = "";
		Long idTipoLogin = 0l;
		Funcionario funcionario = new Funcionario();
		Map<String, String> retorno = new HashMap<String, String>();
		
		if (errors.hasErrors()) {
			errosLogin = errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
		} else {
			funcionario = loginService.executarLogin(dadosLogin);
			
			if (funcionario != null) {				
				if (funcionario.getTipoFuncionario() != null) {
					idTipoLogin = funcionario.getTipoFuncionario();
				} else {
					errosLogin = "Erro ao recuperar o perfil do usuário";
				}
			} else {
				errosLogin = "Dados de Login não encontrados";
			}
		}

		retorno.put("erros", errosLogin);
		retorno.put("tipoLogin", idTipoLogin.toString());
		
		return retorno;
		
	}
}
