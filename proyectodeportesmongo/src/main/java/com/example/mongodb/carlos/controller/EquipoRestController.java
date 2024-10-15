package com.example.mongodb.carlos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodb.carlos.Entity.Asociacion;
import com.example.mongodb.carlos.Entity.Competicion;
import com.example.mongodb.carlos.Entity.Director;
import com.example.mongodb.carlos.Entity.Jugador;
import com.example.mongodb.carlos.Repository.AsociacionRepository;
import com.example.mongodb.carlos.Repository.CompeticionRepository;
import com.example.mongodb.carlos.Repository.DirectorRepository;
import com.example.mongodb.carlos.Repository.JugadorRepository;
import com.example.mongodb.carlos.Entity.Equipo;
import com.example.mongodb.carlos.exception.NotFoundException;
import com.example.mongodb.carlos.Repository.EquipoRepository;



@RestController
@RequestMapping(value = "/api/equipos")
public class EquipoRestController {
	
	 @Autowired
	    private EquipoRepository equipoRepository;

	    @Autowired
	    private AsociacionRepository asociacionRepository;

	    @Autowired
	    private CompeticionRepository competicionRepository;

	    @Autowired
	    private DirectorRepository directorRepository;

	    @Autowired
	    private JugadorRepository jugadorRepository;

	    @GetMapping("/")
	    public List<Equipo> getAllEquipos() {
	        return equipoRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public Equipo getEquipoById(@PathVariable String id) {
	        return equipoRepository.findById(id)
	                .orElseThrow(() -> new NotFoundException("Equipo no encontrado"));
	    }

	    @PostMapping("/")
	    public Equipo saveEquipo(@RequestBody Equipo equipo,
	                             @RequestParam String asociacionId,
	                             @RequestParam List<String> competicionId,
	                             @RequestParam String directorId,
	                             @RequestParam List<String> jugadorId) {
	        // Asignar asociación, competiciones, director y jugadores
	        Asociacion asociacion = asociacionRepository.findById(asociacionId)
	                .orElseThrow(() -> new NotFoundException("Asociación no encontrada"));
	        equipo.setAsociacion(asociacion);

	        List<Competicion> competiciones = competicionRepository.findAllById(competicionId);
	        equipo.setCompeticiones(competiciones);

	        Director director = directorRepository.findById(directorId)
	                .orElseThrow(() -> new NotFoundException("Director no encontrado"));
	        equipo.setDirector(director);

	        List<Jugador> jugadores = jugadorRepository.findAllById(jugadorId);
	        equipo.setJugadores(jugadores);

	        return equipoRepository.save(equipo);
	    }

	    @PutMapping("/{id}")
	    public Equipo updateEquipo(@PathVariable String id,
	                               @RequestBody Equipo equipo,
	                               @RequestParam String asociacionId,
	                               @RequestParam List<String> competicionId,
	                               @RequestParam String directorId,
	                               @RequestParam List<String> jugadorId) {
	        Equipo equipoExistente = equipoRepository.findById(id)
	                .orElseThrow(() -> new NotFoundException("Equipo no encontrado"));

	        Asociacion asociacion = asociacionRepository.findById(asociacionId)
	                .orElseThrow(() -> new NotFoundException("Asociación no encontrada"));
	        equipoExistente.setAsociacion(asociacion);

	        List<Competicion> competiciones = competicionRepository.findAllById(competicionId);
	        equipoExistente.setCompeticiones(competiciones);

	        Director director = directorRepository.findById(directorId)
	                .orElseThrow(() -> new NotFoundException("Director no encontrado"));
	        equipoExistente.setDirector(director);

	        List<Jugador> jugadores = jugadorRepository.findAllById(jugadorId);
	        equipoExistente.setJugadores(jugadores);

	        equipoExistente.setName(equipo.getName());

	        return equipoRepository.save(equipoExistente);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteEquipo(@PathVariable String id) {
	        Equipo equipo = equipoRepository.findById(id)
	                .orElseThrow(() -> new NotFoundException("Equipo no encontrado"));
	        equipoRepository.delete(equipo);
	    }

}