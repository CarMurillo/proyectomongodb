package com.example.mongodb.carlos.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.mongodb.carlos.Entity.Competicion;
import com.example.mongodb.carlos.exception.NotFoundException;
import com.example.mongodb.carlos.Repository.CompeticionRepository;



@RestController
@RequestMapping(value = "/api/competicion")
public class CompeticionRestController {
	
	 @Autowired
	    private CompeticionRepository competicionRepository;
	 
	 @GetMapping("/")
	    public List<Competicion> getAllCompeticion() {
	        return competicionRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public Competicion getCompeticionById(@PathVariable String id) {
	        return competicionRepository.findById(id).orElseThrow(() -> new NotFoundException("Competicion no encontrado"));
	    }

	    @PostMapping("/")
	    public Competicion saveCompeticion(@RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Competicion competicion = mapper.convertValue(body, Competicion.class);
	        return competicionRepository.save(competicion);
	    }

	    @PutMapping("/{id}")
	    public Competicion updateCompeticion(@PathVariable String id, @RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Competicion competicion = mapper.convertValue(body, Competicion.class);
	        competicion.setId(id);
	        return competicionRepository.save(competicion);
	    }

	    @DeleteMapping("/{id}")
	    public Competicion deleteDirector(@PathVariable String id) {
	    	Competicion competicion = competicionRepository.findById(id).orElseThrow(() -> new NotFoundException("Competicion no encontrado"));
	    	competicionRepository.deleteById(id);
	        return competicion;
	    }
	 

}

