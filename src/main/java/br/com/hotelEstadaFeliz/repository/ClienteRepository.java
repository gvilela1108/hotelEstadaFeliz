package br.com.hotelEstadaFeliz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.hotelEstadaFeliz.beans.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> { 

	Cliente findByCpf(Long cpf);
	
}
