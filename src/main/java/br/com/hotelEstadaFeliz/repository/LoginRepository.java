package br.com.hotelEstadaFeliz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.hotelEstadaFeliz.beans.Funcionario;

@Repository
public interface LoginRepository extends MongoRepository<Funcionario, String>{

	Funcionario findByEmailAndSenha(String email, String senha);
}
