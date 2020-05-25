package com.eareiza.calificaciones.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eareiza.calificaciones.model.Alumno;
import com.eareiza.calificaciones.model.Curso;
import com.eareiza.calificaciones.model.CursosActivos;
import com.eareiza.calificaciones.services.ICursoServices;

//Se configura como clase controlador para la clase Curso
@RestController
@RequestMapping("/apiCurso")
public class CursoController {

	//En tiempo de ejecucion se realiza inyeccion de las instancias necesarias 
	@Autowired
	private ICursoServices cursoServ;
	
	//Metodo para crear un curso
	@PostMapping("/save")
	public void guardarCurso(@RequestBody Curso curso) {
		cursoServ.guardarCurso(curso);
	}	
	
	//Metodo que retorna una lista de K cantidad de alumnos de un curso
	@GetMapping("/getAlumnosCurso/{idCurso}/{k}")
	public List<Alumno> getAlumCur(@PathVariable("idCurso") int idCurso, @PathVariable("k") int k, HttpServletResponse response){
		Optional<Curso> curso = cursoServ.getCurso(idCurso);
		List<Alumno> alumnos = new ArrayList<>();
		if(curso.isPresent()){
			alumnos = cursoServ.getAlumnos(idCurso);
			if(alumnos.size()==0) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return alumnos.subList(0, alumnos.size() < k ? alumnos.size() : k);		
	}
	
	
	//Metodo que retorna cursos activos segun fecha indicada
	@GetMapping("/getCursosActivos/{fecha}")  
	public CursosActivos getCursosPorFecha(@PathVariable("fecha")String fechaSt){
		LocalDate fecha = LocalDate.parse(fechaSt);
		return cursoServ.getCursoFecha(fecha);
	}
}
