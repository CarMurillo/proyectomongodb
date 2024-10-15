package com.example.mongodb.carlos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mongodb.carlos.Entity.Asociacion;
import com.example.mongodb.carlos.exception.NotFoundException;
import com.example.mongodb.carlos.Repository.AsociacionRepository;



@Controller
@RequestMapping(value = "asociacion")

public class AsociacionWebController {
	@Autowired
    private AsociacionRepository asociacionRepository;
	
	  @GetMapping("/")
	    public String asociacionListTemplate(Model model) {
	        model.addAttribute("asociacion", asociacionRepository.findAll());
	        return "asociacion-list";
	    }

	    @GetMapping("/new")
	    public String asociacionNewTemplate(Model model) {
	        model.addAttribute("asociacion", new Asociacion());
	        return "asociacion-form";
	    }

	    @GetMapping("/edit/{id}")
	    public String asociacionEditTemplate(@PathVariable("id") String id, Model model) {
	        model.addAttribute("asociacion",asociacionRepository.findById(id).orElseThrow(() -> new NotFoundException("Asociacion no encontrado")));
	        return "asociacion-form";
	    }

	    @PostMapping("/save")
	    public String asociacionSaveProcess(@ModelAttribute("asociacion") Asociacion asociacion) {
	        if (asociacion.getId().isEmpty()) {
	        	asociacion.setId(null);
	        }
	        asociacionRepository.save(asociacion);
	        return "redirect:/asociacion/";
	    }

	    @GetMapping("/delete/{id}")
	    public String asociacionDeleteProcess(@PathVariable("id") String id) {
	    	asociacionRepository.deleteById(id);
	        return "redirect:/asociacion/";
	    }
	}

