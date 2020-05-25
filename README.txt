
//Crea un curso
localhost:8080/apiCurso/save

body:
{
	"nombre":"Ingles",
	"fechaInicio":"2020-05-01",
	"fechaFin":"2020-05-30",
	"horasSemanales":"4"
}

//Crea un alumno
localhost:8080/api/save
{
	"nombre":"Antonio",
	"apellido":"Pinango",
	"libreta":"29530651",
	"fechaNacimiento":"2001-05-26"
}

//Registra un alumno en un curso, el alumno no puede tener mas de 20 horas semanales
localhost:8080/api/registro/{idAlumno}/{idCurso}

//Busca cursos activos segun una fecha especifica
localhost:8080/apiCurso/getCursosActivos/{yyyy-MM-dd}

//Retorna una lista de K alumnos segun el id del curso
localhost:8080/apiCurso/getAlumnosCurso/{idCurso}/{K}




