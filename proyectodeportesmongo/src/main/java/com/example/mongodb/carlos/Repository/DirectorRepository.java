package com.example.mongodb.carlos.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mongodb.carlos.Entity.Director;

public interface DirectorRepository extends MongoRepository<Director, String> {

}
