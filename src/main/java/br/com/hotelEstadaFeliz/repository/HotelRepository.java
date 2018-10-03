package br.com.hotelEstadaFeliz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.hotelEstadaFeliz.beans.Hotel;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String> { 

	Hotel findByCnpj(Long cnpj);
	
}
