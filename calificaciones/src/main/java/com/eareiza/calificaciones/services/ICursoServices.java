package com.eareiza.calificaciones.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.eareiza.calificaciones.model.Alumno;
import com.eareiza.calificaciones.model.Curso;
import com.eareiza.calificaciones.model.CursosActivos;

//Se crea interface correspondiente a la clase curso para realizar la inyeccion de dependencia
public interface ICursoServices {
	List<Curso> getCursos();
	void guardarCurso(Curso curso);
	Optional<Curso> getCurso(int idCurso);
	List<Alumno> getAlumnos(int idCurso);
	CursosActivos getCursoFecha(LocalDate fecha);
}
