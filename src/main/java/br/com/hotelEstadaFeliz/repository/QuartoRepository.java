package br.com.hotelEstadaFeliz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.hotelEstadaFeliz.beans.Quarto;

@Repository
public interface QuartoRepository extends MongoRepository<Quarto, String>{

	Quarto findByNumeroAndIdHotel(String numero, String idHotel);

}
