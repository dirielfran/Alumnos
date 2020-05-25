package com.eareiza.calificaciones.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eareiza.calificaciones.model.Alumno;

//Se crea Repositrorio para la clase Alumno
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

}
