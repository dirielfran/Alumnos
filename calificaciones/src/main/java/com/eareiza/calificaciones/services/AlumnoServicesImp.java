package com.eareiza.calificaciones.services;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.eareiza.calificaciones.model.Alumno;
import com.eareiza.calificaciones.model.Curso;
import com.eareiza.calificaciones.repository.AlumnoRepository;

//Clase de Servicio para la clase Alumno
@Service
@Primary
public class AlumnoServicesImp implements IAlumnoServices{

	//En tiempo de ejecucion se realiza inyeccion de las instancias necesarias 
	@Autowired
	private AlumnoRepository alumnoRepo;
	
	//Metodo que retorna todos los cursos
	@Override
	public List<Alumno> buscarTodos() {
		return alumnoRepo.findAll();
	}

	//Metodo que guarda un alumno
	public void guardarAlumno(Alumno alumno) {
		alumnoRepo.save(alumno);		
	}

	//Metodo que retorna un alumno por id
	@Override
	public Optional<Alumno> getAlumno(int idAlumno) {
		return alumnoRepo.findById(idAlumno);
	}	
	
	//Metodo que valida que un alumno no tenga mas de 20 horas semanalas registradas
	@Override
	public boolean validaCurso(Alumno alumno, Curso curso) {
		boolean valida = false;
		//atributo que almacena horas si tiene cursos registrados en la misma semana
		int horasValida = curso.getHorasSemanales();
		//Almacena entero que representa la semana del inicio del curso que se quiere registrar
		int semIni = semYear(curso.getFechaInicio());
		//Almacena entero que representa la semana de finalizacion del curso que se quiere registrar
		int semFin = semYear(curso.getFechaFin());
		//Recorre los alumnos del curso
		for (Curso obj : alumno.getCursos()) {
			//Almacena semana inicio de curso registrado
			int ini = semYear(obj.getFechaInicio());
			//Almacena semana fin de curso registrado
			int fin = semYear(obj.getFechaFin());
			//valida si las fechas del curso que se quiere registrar se cruza con los cursos registrados
			if(semIni >= ini && semIni <= fin) {
				if(semFin >= ini && semFin <= fin) {
					horasValida += obj.getHorasSemanales();
				}
			}
		}
		valida = (horasValida > 20)?  false :  true;
		return valida;
	}
	
	//Metodo que devuelve un entero que representa la semana en el año
	public int semYear(LocalDate fecha) {
		Calendar cal = Calendar.getInstance();
		cal.set(fecha.getYear(), fecha.getMonthValue()-1, fecha.getDayOfMonth());
		//Se configura que el dia lunes sera el inicio de la semana
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

}
