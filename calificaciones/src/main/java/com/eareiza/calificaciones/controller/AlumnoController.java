package com.eareiza.calificaciones.controller;


import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eareiza.calificaciones.model.Alumno;
import com.eareiza.calificaciones.model.Curso;
import com.eareiza.calificaciones.services.IAlumnoServices;
import com.eareiza.calificaciones.services.ICursoServices;

//Se configura como clase controlador para la clase Alumno
@RestController
@RequestMapping("/api")
public class AlumnoController {

	//En tiempo de ejecucion se realiza inyeccion de las instancias necesarias 
	@Autowired
	private IAlumnoServices alumnoServ;
	@Autowired
	private ICursoServices cursoServ;

	//Metodo que retorna un alumno por id
	@GetMapping("/getAlumno/{id}")
	public ResponseEntity<Alumno> getAlumno(@PathVariable("id") int idAlumno) {
		Optional<Alumno> alumno = alumnoServ.getAlumno(idAlumno);
		if (alumno.isPresent()) {
			return new ResponseEntity<>(alumno.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	//Metodo para la creacion de un alumno
	@PostMapping("/save")
	public void guardarAlumno(@RequestBody Alumno alumno) {
		alumnoServ.guardarAlumno(alumno);
	}

	//Metodo para el registro de un alumno en un curso
	@PostMapping("/registro/{idAlumno}/{idCurso}")
	public void regisAlumno(@PathVariable("idAlumno") int idAlumno, @PathVariable("idCurso") int idCurso,
			HttpServletResponse response) {
		Alumno alumno = null;
		Curso curso = null;
		Optional<Alumno> alum = alumnoServ.getAlumno(idAlumno);
		Optional<Curso> cur = cursoServ.getCurso(idCurso);
		//Se valida si existe tanto el curso como el alumno
		if (alum.isPresent() && cur.isPresent()) {
			alumno = alum.get();
			curso = cur.get();
			//Se valida si el alumno tiene mas de 20  horas inscriptas por semana
			if (alumnoServ.validaCurso(alumno, curso)) {
				alumno.agregaCurso(curso);
				alumnoServ.guardarAlumno(alumno);
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
