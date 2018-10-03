package br.com.hotelEstadaFeliz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.hotelEstadaFeliz.beans.Hospedagem;

@Repository
public interface HospedagemRepository extends MongoRepository<Hospedagem, String> { 

	Hospedagem findByIdClienteAndIdQuartoAndIdHotel(String idCliente,String idQuarto, String idHotel);
	
}