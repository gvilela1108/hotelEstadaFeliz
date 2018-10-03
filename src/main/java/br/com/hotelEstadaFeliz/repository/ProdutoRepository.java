package br.com.hotelEstadaFeliz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.hotelEstadaFeliz.beans.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String>{

	Produto findByNome(String nome);
	
}
