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
import com.example.mongodb.carlos.Entity.Asociacion;
import com.example.mongodb.carlos.exception.NotFoundException;
import com.example.mongodb.carlos.Repository.AsociacionRepository;



@RestController
@RequestMapping(value = "/api/asociacion")
public class AsociacionRestController {
	
	 @Autowired
	    private AsociacionRepository asociacionRepository;
	 
	 @GetMapping("/")
	    public List<Asociacion> getAllAsociacion() {
	        return asociacionRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public Asociacion getAsociacionById(@PathVariable String id) {
	        return asociacionRepository.findById(id).orElseThrow(() -> new NotFoundException("Asociacion no encontrado"));
	    }

	    @PostMapping("/")
	    public Asociacion saveAsociacion(@RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Asociacion asociacion = mapper.convertValue(body, Asociacion.class);
	        return asociacionRepository.save(asociacion);
	    }

	    @PutMapping("/{id}")
	    public Asociacion updateAsociacion(@PathVariable String id, @RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Asociacion asociacion = mapper.convertValue(body, Asociacion.class);
	        asociacion.setId(id);
	        return asociacionRepository.save(asociacion);
	    }

	    @DeleteMapping("/{id}")
	    public Asociacion deleteAsociacion(@PathVariable String id) {
	    	Asociacion asociacion = asociacionRepository.findById(id).orElseThrow(() -> new NotFoundException("Asociacion no encontrado"));
	    	asociacionRepository.deleteById(id);
	        return asociacion;
	    }
	 

}

