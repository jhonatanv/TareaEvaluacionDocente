package co.edu.eam.ingesoft.pa2.tareaopenshift.bos;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import co.edu.eam.ingesoft.pa2.tareaopenshift.ejbremote.CalificadoRemoteEJB;
import co.edu.eam.ingesoft.pa2.tareaopenshift.implementacion.EJBGenerico;
import co.edu.eam.ingesoft.pa2.tareaopenshift.persistencia.entidades.Calificado;

@LocalBean
@Stateless
@Remote(CalificadoRemoteEJB.class)
public class CalificadoEJB extends EJBGenerico<Calificado> implements CalificadoRemoteEJB{

	@Override
	public Class getClase() {
		// TODO Auto-generated method stub
		return Calificado.class;
	}

	public void crear(Calificado calificado){
		dao.crear(calificado);
	}
}
