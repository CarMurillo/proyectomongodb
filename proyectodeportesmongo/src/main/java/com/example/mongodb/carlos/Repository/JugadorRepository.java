package com.example.mongodb.carlos.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.mongodb.carlos.Entity.Jugador;

public interface JugadorRepository extends MongoRepository<Jugador, String> {
	
}
