package com.example.mongodb.carlos.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "competicion")
public class Competicion {
	@Id
	private String id;
	private String nombre;
	private String premio;
	private String fechainicio;
	private String fechaFin;
	
	public Competicion() {
	
}

	public Competicion(String id, String nombre, String premio, String fechainicio, String fechaFin) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.premio = premio;
		this.fechainicio = fechainicio;
		this.fechaFin = fechaFin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPremio() {
		return premio;
	}

	public void setPremio(String premio) {
		this.premio = premio;
	}

	public String getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(String fechainicio) {
		this.fechainicio = fechainicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

}
