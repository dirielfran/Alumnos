package com.eareiza.calificaciones.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eareiza.calificaciones.model.Alumno;
import com.eareiza.calificaciones.model.Curso;

//Se crea Repositrorio para la clase Curso
@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
	//Query method que retorna una lista de alumnos por curso
	@Query(value="select a from Curso c inner join c.alumnos a where c.id = ?1 order by a.fechaNacimiento desc ")
	List<Alumno> findAlumnosByCursoId(int idCurso);
	//Query method que retorna una lista de cursos activos segun una fecha especifica
	@Query(value="select c from Curso c where c.fechaInicio <= ?1 and c.fechaFin >= ?1  ")
	List<Curso> findCursoFecha(LocalDate fecha);
}
