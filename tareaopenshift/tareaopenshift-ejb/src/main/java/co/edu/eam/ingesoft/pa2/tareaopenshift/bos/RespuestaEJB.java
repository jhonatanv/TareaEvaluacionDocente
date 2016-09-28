package co.edu.eam.ingesoft.pa2.tareaopenshift.bos;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import co.edu.eam.ingesoft.pa2.tareaopenshift.ejbremote.RespPregRemoteEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.ejbremote.RespuestaRemoteEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.pa2.tareaopenshift.implementacion.EJBGenerico;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.dto.RespuestaEvaluacionDTO;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.dto.RespuestaPreguntaDTO;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Calificado;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Evaluacion;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Grupo;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.PregEval;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Pregunta;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.RespPreg;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Respuesta;

@LocalBean
@Stateless
@Remote(RespPregRemoteEJB.class)
public class RespuestaEJB extends EJBGenerico<Respuesta> implements RespuestaRemoteEJB {

	@EJB
	private RespPregEJB respPregEJB;
	
	@EJB
	private CalificadoEJB calificadoEJB;

	@EJB
	private GrupoEJB grupoEJB;

	@EJB
	private EvaluacionEJB evaluacionEJB;

	@EJB
	private PregEvalEJB pregEvalEJB;

	@EJB
	private PreguntaEJB preguntaEJB;

	@Override
	public Class getClase() {
		return Respuesta.class;
	}

	public void crear(Respuesta respuesta) {
		if (buscar(respuesta.getIdRespuestas()) == null) {
			dao.crear(respuesta);
		} else {
			throw new ExcepcionNegocio("Esta respuesta ya se encuentra registrada");
		}
	}

	public Respuesta buscar(Object pk) {
		return dao.buscar(pk);
	}


	public boolean responderEvaluacion(RespuestaEvaluacionDTO resp) {

		if (resp != null) {
			Grupo grup = grupoEJB.buscar(resp.getIdGrupo());
			Respuesta respuesta = new Respuesta(grup, GregorianCalendar.getInstance().getTime(),
					resp.getComentario());
			crear(respuesta);

	        
			Evaluacion eval = new Evaluacion(GregorianCalendar.getInstance().getTime(), "calificado", grup.getAnio() , grup.getPeriodo() );
			
			for (RespuestaPreguntaDTO respuestraPregunta : resp.getRespuesta()) {
				Pregunta preg = preguntaEJB.buscar(respuestraPregunta.getIdPregunta());
				PregEval pregEvaluacion = new PregEval(preg, eval);
				pregEvalEJB.crear(pregEvaluacion);
				
				RespPreg respPreg = new RespPreg(pregEvaluacion, respuesta, respuestraPregunta.getCalificacion());
				respPregEJB.crear(respPreg);
				

			}
			Calificado ca = new Calificado(resp.getCodigoEstudiante(), grup, true);				
             calificadoEJB.crear(ca);
			return true;
		} else {
			return false;
		}

	}

}
