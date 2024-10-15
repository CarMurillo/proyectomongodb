package com.example.mongodb.carlos.Entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "equipos")
public class Equipo {
	
	@Id
	private String id;
	
	private String name;
	
	private Director director;
	
	private List<Jugador>jugadores;
	
	private Asociacion asociacion;

	private List<Competicion> competiciones;

	public Equipo() {
		// TODO Auto-generated constructor stub
	}

	public Equipo(String id, String name, Director director, List<Jugador> jugadores, Asociacion asociacion,
			List<Competicion> competiciones) {
		this.id = id;
		this.name = name;
		this.director = director;
		this.jugadores = jugadores;
		this.asociacion = asociacion;
		this.competiciones = competiciones;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Asociacion getAsociacion() {
		return asociacion;
	}

	public void setAsociacion(Asociacion asociacion) {
		this.asociacion = asociacion;
	}

	public List<Competicion> getCompeticiones() {
		return competiciones;
	}

	public void setCompeticiones(List<Competicion> competiciones) {
		this.competiciones = competiciones;
	}
	
}