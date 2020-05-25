package com.eareiza.calificaciones.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="alumno")
public class Alumno {
	
	//Se configura el id clave primaria y su generacion automatica 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String apellido;
	private String libreta;
	private LocalDate fechaNacimiento;
	
	
	//Se configura una relacion de Muchos a Muchos con la tabla alumnos
	@ManyToMany(fetch=FetchType.LAZY)
	/*Se configura la anotacion @JsonIgnore en el atributo para ocultar
	 *  los atributos del analizador Jackson durante la serializacion
	 */
	@JsonIgnore
	@JoinTable(name="alumnocurso",
				joinColumns=@JoinColumn(name="idAlumno"),
				inverseJoinColumns = @JoinColumn(name="idCurso"))
	private List<Curso> cursos;
	
	//Metodo que se utiliza agregar curso al alumno
	public void agregaCurso(Curso curso) {
		if(cursos == null) {
			cursos = new LinkedList<>();
		}
		cursos.add(curso);
	}
	
	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getLibreta() {
		return libreta;
	}

	public void setLibreta(String libreta) {
		this.libreta = libreta;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", libreta=" + libreta
				+ ", fechaNacimiento=" + fechaNacimiento + ", cursos=" + cursos + "]";
	}	
}
