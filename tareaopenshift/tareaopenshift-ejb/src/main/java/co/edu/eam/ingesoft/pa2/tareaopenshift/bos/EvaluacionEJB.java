package co.edu.eam.ingesoft.pa2.tareaopenshift.bos;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import co.edu.eam.ingesoft.pa2.tareaopenshift.ejbremote.EvaluacionRemoteEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.pa2.tareaopenshift.implementacion.EJBGenerico;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Evaluacion;

@LocalBean
@Stateless
@Remote(EvaluacionRemoteEJB.class)
public class EvaluacionEJB extends EJBGenerico<Evaluacion> implements EvaluacionRemoteEJB {

	@Override
	public Class getClase() {
		return Evaluacion.class;
	}
	
	public Evaluacion buscar(Object pk) {
		return dao.buscar(pk);
	}

	public void crear(Evaluacion evaluacion) {
		if (buscar(evaluacion.getIdEvaluacion()) == null) {
			dao.crear(evaluacion);
		} else {
			throw new ExcepcionNegocio("Esta evaluacion ya se encuentra registrada");
		}
	}

	

}
