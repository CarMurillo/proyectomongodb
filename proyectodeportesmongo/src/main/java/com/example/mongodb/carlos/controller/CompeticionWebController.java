package com.example.mongodb.carlos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mongodb.carlos.Entity.Competicion;
import com.example.mongodb.carlos.exception.NotFoundException;
import com.example.mongodb.carlos.Repository.CompeticionRepository;


@Controller
@RequestMapping(value = "competicion")

public class CompeticionWebController {
	@Autowired
    private CompeticionRepository competicionRepository;
	
	  @GetMapping("/")
	    public String competicionListTemplate(Model model) {
	        model.addAttribute("competicion", competicionRepository.findAll());
	        return "competicion-list";
	    }

	    @GetMapping("/new")
	    public String competicionNewTemplate(Model model) {
	        model.addAttribute("competicion", new Competicion());
	        return "competicion-form";
	    }

	    @GetMapping("/edit/{id}")
	    public String CompeticionEditTemplate(@PathVariable("id") String id, Model model) {
	        model.addAttribute("competicion",competicionRepository.findById(id).orElseThrow(() -> new NotFoundException("Competicion no encontrado")));
	        return "competicion-form";
	    }

	    @PostMapping("/save")
	    public String competicionSaveProcess(@ModelAttribute("director") Competicion competicion) {
	        if (competicion.getId().isEmpty()) {
	        	competicion.setId(null);
	        }
	        competicionRepository.save(competicion);
	        return "redirect:/competicion/";
	    }

	    @GetMapping("/delete/{id}")
	    public String competicionDeleteProcess(@PathVariable("id") String id) {
	    	competicionRepository.deleteById(id);
	        return "redirect:/competicion/";
	    }
	}

