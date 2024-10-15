package com.example.mongodb.carlos.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mongodb.carlos.Entity.Asociacion;

public interface AsociacionRepository extends MongoRepository<Asociacion, String> {

}
