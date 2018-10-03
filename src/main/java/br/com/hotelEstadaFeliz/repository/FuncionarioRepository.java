package br.com.hotelEstadaFeliz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.hotelEstadaFeliz.beans.Funcionario;

@Repository
public interface FuncionarioRepository extends MongoRepository<Funcionario, String> { 

	Funcionario findByCpf(Long cpf);
	
}