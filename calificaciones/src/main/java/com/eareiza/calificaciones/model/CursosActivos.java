package com.eareiza.calificaciones.model;

//DTO para la tranferencia de dinformacion
public class CursosActivos {
	private int horasSemanalesTotales;
	private int cantidadDeAlumnos;
	private int edadAlumnosPromedio;
	
	public CursosActivos() {
		super();
	}
	public int getHorasSemanalesTotales() {
		return horasSemanalesTotales;
	}
	public void setHorasSemanalesTotales(int horasSemanalesTotales) {
		this.horasSemanalesTotales = horasSemanalesTotales;
	}
	public int getCantidadDeAlumnos() {
		return cantidadDeAlumnos;
	}
	public void setCantidadDeAlumnos(int cantidadDeAlumnos) {
		this.cantidadDeAlumnos = cantidadDeAlumnos;
	}
	public int getEdadAlumnosPromedio() {
		return edadAlumnosPromedio;
	}
	public void setEdadAlumnosPromedio(int edadAlumnosPromedio) {
		this.edadAlumnosPromedio = edadAlumnosPromedio;
	}
	@Override
	public String toString() {
		return "CursosActivos [horasSemanalesTotales=" + horasSemanalesTotales + ", cantidadDeAlumnos="
				+ cantidadDeAlumnos + ", edadAlumnosPromedio=" + edadAlumnosPromedio + "]";
	}
	
}
