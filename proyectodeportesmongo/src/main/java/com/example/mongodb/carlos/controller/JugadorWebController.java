package com.example.mongodb.carlos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mongodb.carlos.Entity.Jugador;
import com.example.mongodb.carlos.exception.NotFoundException;
import com.example.mongodb.carlos.Repository.JugadorRepository;



@Controller
@RequestMapping(value = "jugadores")

public class JugadorWebController {
	@Autowired
    private JugadorRepository jugadorRepository;
	
	  @GetMapping("/")
	    public String jugadorListTemplate(Model model) {
	        model.addAttribute("jugador", jugadorRepository.findAll());
	        return "jugador-list";
	    }

	    @GetMapping("/new")
	    public String jugadorNewTemplate(Model model) {
	        model.addAttribute("jugador", new Jugador());
	        return "jugador-form";
	    }

	    @GetMapping("/edit/{id}")
	    public String jugadorEditTemplate(@PathVariable("id") String id, Model model) {
	        model.addAttribute("jugador",jugadorRepository.findById(id).orElseThrow(() -> new NotFoundException("jugador no encontrado")));
	        return "jugador-form";
	    }

	    @PostMapping("/save")
	    public String jugadoresSaveProcess(@ModelAttribute("jugadores") Jugador jugador) {
	        if (jugador.getId().isEmpty()) {
	        	jugador.setId(null);
	        }
	        jugadorRepository.save(jugador);
	        return "redirect:/jugadores/";
	    }

	    @GetMapping("/delete/{id}")
	    public String jugadorDeleteProcess(@PathVariable("id") String id) {
	    	jugadorRepository.deleteById(id);
	        return "redirect:/jugadores/";
	    }
	}
