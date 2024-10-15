package com.example.mongodb.carlos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import com.example.mongodb.carlos.Repository.JugadorRepository;
import com.example.mongodb.carlos.Entity.Jugador;
import com.example.mongodb.carlos.Entity.Asociacion;
import com.example.mongodb.carlos.Entity.Competicion;
import com.example.mongodb.carlos.Entity.Director;
import com.example.mongodb.carlos.Entity.Equipo;
import com.example.mongodb.carlos.exception.NotFoundException;
import com.example.mongodb.carlos.Repository.AsociacionRepository;
import com.example.mongodb.carlos.Repository.CompeticionRepository;
import com.example.mongodb.carlos.Repository.DirectorRepository;
import com.example.mongodb.carlos.Repository.EquipoRepository;



@Controller
@RequestMapping(value = "equipos")

public class EquipoWebContoller {
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
	    public String equiposListTemplate(Model model) {
	        model.addAttribute("equipos", equipoRepository.findAll());
	        return "equipos-list";
	    }

	    @GetMapping("/new")
	    public String equiposNewTemplate(Model model) {
	        model.addAttribute("equipo", new Equipo());
	        model.addAttribute("asociaciones", asociacionRepository.findAll());
	        model.addAttribute("competiciones", competicionRepository.findAll());
	        model.addAttribute("directores", directorRepository.findAll());
	        model.addAttribute("jugadores", jugadorRepository.findAll());
	        return "equipos-form";
	    }

	    @GetMapping("/{id}")
	    public String equipoDetailTemplate(@PathVariable String id, Model model) {
	        Equipo equipo = equipoRepository.findById(id)
	                .orElseThrow(() -> new NotFoundException("Equipo no encontrado"));
	        model.addAttribute("equipo", equipo);
	        model.addAttribute("asociaciones", asociacionRepository.findAll());
	        model.addAttribute("competiciones", competicionRepository.findAll());
	        model.addAttribute("directores", directorRepository.findAll());
	        model.addAttribute("jugadores", jugadorRepository.findAll());
	        return "equipos-form";
	    }

	    @PostMapping("/save")
	    public String saveEquipo(Equipo equipo, @RequestParam String asociacionId,
	                             @RequestParam List<String> competicionId, @RequestParam String directorId,
	                             @RequestParam List<String> jugadorId) {
	        Asociacion asociacion = asociacionRepository.findById(asociacionId)
	                .orElseThrow(() -> new NotFoundException("Asociación no encontrada"));
	        List<Competicion> competicion = competicionRepository.findAllById(competicionId);
	        Director director = directorRepository.findById(directorId)
	                .orElseThrow(() -> new NotFoundException("Director no encontrado"));
	        List<Jugador> jugador = jugadorRepository.findAllById(jugadorId);

	        equipo.setAsociacion(asociacion);
	        equipo.setCompeticiones(competicion);
	        equipo.setDirector(director);
	        equipo.setJugadores(jugador);
	        
	        if (equipo.getId().isEmpty()) {
	        	equipo.setId(null);
	        }
	        equipoRepository.save(equipo);
	        return "redirect:/equipos/";
	    }


	    @GetMapping("/edit/{id}")
	    public String editEquipo(@PathVariable String id, Model model) {
	        Equipo equipo = equipoRepository.findById(id)
	                .orElseThrow(() -> new NotFoundException("Equipo no encontrado"));
	        model.addAttribute("equipo", equipo);
	        model.addAttribute("asociaciones", asociacionRepository.findAll());
	        model.addAttribute("competiciones", competicionRepository.findAll());
	        model.addAttribute("directores", directorRepository.findAll());
	        model.addAttribute("jugadores", jugadorRepository.findAll());
	        return "equipos-form";
	    }

	    @PostMapping("/update")
	    public String updateEquipo(Equipo equipo, @RequestParam String asociacionId,
	                               @RequestParam List<String> competicionId, @RequestParam String directorId,
	                               @RequestParam List<String> jugadorId) {
	        Asociacion asociacion = asociacionRepository.findById(asociacionId)
	                .orElseThrow(() -> new NotFoundException("Asociación no encontrada"));
	        List<Competicion> competicion = competicionRepository.findAllById(competicionId);
	        Director director = directorRepository.findById(directorId)
	                .orElseThrow(() -> new NotFoundException("Director no encontrado"));
	        List<Jugador> jugador = jugadorRepository.findAllById(jugadorId);

	        equipo.setAsociacion(asociacion);
	        equipo.setCompeticiones(competicion);
	        equipo.setDirector(director);
	        equipo.setJugadores(jugador);

	        equipoRepository.save(equipo);
	        return "redirect:/equipos/";
	    }

	    @GetMapping("/delete/{id}")
	    public String deleteEquipo(@PathVariable String id) {
	        equipoRepository.deleteById(id);
	        return "redirect:/equipos/";
	    }
	}