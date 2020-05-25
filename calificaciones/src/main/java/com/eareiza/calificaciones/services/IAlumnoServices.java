package com.eareiza.calificaciones.services;

import java.util.List;
import java.util.Optional;

import com.eareiza.calificaciones.model.Alumno;
import com.eareiza.calificaciones.model.Curso;

//Se crea interface correspondiente a la clase alumno para realizar la inyeccion de dependencia
public interface IAlumnoServices {
	List<Alumno> buscarTodos();
	Optional<Alumno> getAlumno(int idAlumno);
	void guardarAlumno(Alumno alumno);
	boolean validaCurso(Alumno alumno, Curso curso);
}
