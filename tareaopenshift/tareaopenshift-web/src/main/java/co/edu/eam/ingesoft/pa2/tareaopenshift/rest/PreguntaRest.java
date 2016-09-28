package co.edu.eam.ingesoft.pa2.tareaopenshift.rest;

import java.util.List;



import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.PreguntaEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.bos.RespuestaEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.interceptores.Secured;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.dto.RespuestaEvaluacionDTO;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Pregunta;
import co.edu.eam.ingesoft.pa2.tareaopenshift.rest.dto.RespuestaDTO;


@Secured
@Path("/pregunta")
public class PreguntaRest {
	
	@EJB
	private RespuestaEJB respuestaEjb;

	@EJB
	private PreguntaEJB preguntaEjb;
	
	@Path("/listarPreguntas")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarPreguntas() {
		List<Pregunta> preguntas = preguntaEjb.listarPregunta();
		if (preguntas.isEmpty()) {
			return new RespuestaDTO(null, "no hay preguntas", "-1");
		} else {
			return new RespuestaDTO(preguntas);
		}
	}
	
	
	
	@POST
	@Path("/responderEvaluacion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO calificar(RespuestaEvaluacionDTO respuesta){
		
		if(respuestaEjb.responderEvaluacion(respuesta)==true){
			return new RespuestaDTO(true, "se evaluo correctamente", "00");
		}else{
			return new RespuestaDTO(false, "error, no se evaluo", "-1");
		}
	}
}
