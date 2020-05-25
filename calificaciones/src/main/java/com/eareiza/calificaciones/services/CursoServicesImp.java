package com.eareiza.calificaciones.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eareiza.calificaciones.model.Alumno;
import com.eareiza.calificaciones.model.Curso;
import com.eareiza.calificaciones.model.CursosActivos;
import com.eareiza.calificaciones.repository.CursoRepository;

//Clase de Servicio para la clase Curso
@Service
public class CursoServicesImp implements ICursoServices {

	//En tiempo de ejecucion se realiza inyeccion de las instancias necesarias 
	@Autowired
	private CursoRepository cursoRepo;
	
	//Metodo que retorna una lista de todos los cursos
	@Override
	public List<Curso> getCursos() {
		return cursoRepo.findAll();
	}

	//Metodo que crea un Curso
	@Override
	public void guardarCurso(Curso curso) {
		cursoRepo.save(curso);
	}

	//Metodo que retorna un Curso por Id
	@Override
	public Optional<Curso> getCurso(int idCurso) {
		return cursoRepo.findById(idCurso);
	}
	
	//Metodo que retorna una lista de alumnos por curso
	@Override
	public List<Alumno> getAlumnos(int idCurso) {
		return cursoRepo.findAlumnosByCursoId(idCurso);
	}

	//Metodo que recgrasa estructura de datos de los cursos activos segun fecha
	@Override
	public CursosActivos getCursoFecha(LocalDate fecha) {
		//Se recupera la lista de cursos activos segun la fecha
		List<Curso> cursos = cursoRepo.findCursoFecha(fecha);
		//Se instancia DTO
		CursosActivos cursoActivo = new CursosActivos();
		int canAlumnos = 0;
		int horasTotales = 0;
		int edadTotal = 0; 
		//Se recorre la lista de cursos para almacenar los datos necesarios para crear la estructura de datos
		for (Curso curso : cursos) {
			//Se recupera la lista de alumnos por curso
			List<Alumno> alumnos = curso.getAlumnos();
			//Se incrementa con la cantidad de alumnos por curso a la cantidad total de alumnos
			canAlumnos += alumnos.size();
			//Se incrementa con la cantidad de horas semanales por curso a la cantidad total de horas
			horasTotales += (alumnos.size()*curso.getHorasSemanales());
			//Se recorre lista de alumnos para calcular la edad de cada uno
			for (Alumno alumno : alumnos) {
				LocalDate nacimiento = alumno.getFechaNacimiento();
				LocalDate ahora = LocalDate.now();
				Period periodo = Period.between(nacimiento, ahora);
				edadTotal += periodo.getYears();			
			}
		}
		//Se setan atributos al DTO
		cursoActivo.setEdadAlumnosPromedio((canAlumnos!=0)?edadTotal/canAlumnos:0);
		cursoActivo.setHorasSemanalesTotales(horasTotales);
		cursoActivo.setCantidadDeAlumnos(canAlumnos);
		return cursoActivo;
	}
}
