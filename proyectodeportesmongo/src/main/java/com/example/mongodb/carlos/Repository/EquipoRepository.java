package com.example.mongodb.carlos.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mongodb.carlos.Entity.Equipo;

public interface EquipoRepository extends MongoRepository<Equipo, String> {

}