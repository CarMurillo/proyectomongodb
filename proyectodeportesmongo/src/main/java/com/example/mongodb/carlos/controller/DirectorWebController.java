package com.example.mongodb.carlos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mongodb.carlos.Entity.Director;
import com.example.mongodb.carlos.exception.NotFoundException;
import com.example.mongodb.carlos.Repository.DirectorRepository;



@Controller
@RequestMapping(value = "director")

public class DirectorWebController {
	@Autowired
    private DirectorRepository directorRepository;
	
	  @GetMapping("/")
	    public String directorListTemplate(Model model) {
	        model.addAttribute("director", directorRepository.findAll());
	        return "director-list";
	    }

	    @GetMapping("/new")
	    public String directorNewTemplate(Model model) {
	        model.addAttribute("director", new Director());
	        return "director-form";
	    }

	    @GetMapping("/edit/{id}")
	    public String directorEditTemplate(@PathVariable("id") String id, Model model) {
	        model.addAttribute("director",directorRepository.findById(id).orElseThrow(() -> new NotFoundException("Director no encontrado")));
	        return "director-form";
	    }

	    @PostMapping("/save")
	    public String directorSaveProcess(@ModelAttribute("director") Director director) {
	        if (director.getId().isEmpty()) {
	        	director.setId(null);
	        }
	        directorRepository.save(director);
	        return "redirect:/director/";
	    }

	    @GetMapping("/delete/{id}")
	    public String directorDeleteProcess(@PathVariable("id") String id) {
	    	directorRepository.deleteById(id);
	        return "redirect:/director/";
	    }
	}
