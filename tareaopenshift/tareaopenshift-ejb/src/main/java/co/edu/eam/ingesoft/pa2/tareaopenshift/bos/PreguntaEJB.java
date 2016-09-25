package co.edu.eam.ingesoft.pa2.tareaopenshift.bos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import co.edu.eam.ingesoft.pa2.tareaopenshift.ejbremote.PreguntaRemoteEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.pa2.tareaopenshift.implementacion.EJBGenerico;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Pregunta;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Programa;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Usuario;

@LocalBean
@Stateless
@Remote(PreguntaRemoteEJB.class)
public class PreguntaEJB extends EJBGenerico<Pregunta> implements PreguntaRemoteEJB {

	@Override
	public Class getClase() {
		// TODO Auto-generated method stub
		return Pregunta.class;
	}

	public void crear(Pregunta pregunta) throws ExcepcionNegocio {
		if (buscar(pregunta.getIdPregunta()) != null) {
			throw new ExcepcionNegocio("Esta pregunta ya se encuentra registrada");
		} else {
			dao.crear(pregunta);
		}

	}

	public Pregunta buscar(Object pk) {
		return dao.buscar(pk);
	}

	@Override
	public List<Pregunta> listarPregunta() {
		return dao.ejecutarNamedQuery(Pregunta.LISTA_PREGUNTAS);
	}
}
